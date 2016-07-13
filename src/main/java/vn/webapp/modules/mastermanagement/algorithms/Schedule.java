package vn.webapp.modules.mastermanagement.algorithms;

import java.util.ArrayList;
import java.util.Random;

import localsearch.constraints.basic.Implicate;
import localsearch.constraints.basic.IsEqual;
import localsearch.constraints.basic.NotEqual;
import localsearch.functions.element.Element;
import localsearch.functions.sum.Sum;
import localsearch.model.ConstraintSystem;
import localsearch.model.IFunction;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import localsearch.search.TabuSearch;


public class Schedule {
	// data input
		ArrayList<ArrayList<Integer>> scheduledata;
		int nbCommittee;
		int nbHostProfessor;
		int nbGuestProfessor;
		int nbSlot;
		int nbRoom;
		int[][] hostProfessorScore;
		int[][] guestProfessorScore;
		
		// model
		LocalSearchManager ls;
		ConstraintSystem S;
		int[] x_supervisor;
		VarIntLS[] x_examiner1;
		VarIntLS[] x_examiner2;
		VarIntLS[] x_chairman;
		VarIntLS[] x_secretary;
		VarIntLS[] x_additionalmember;
		VarIntLS[] x_slot;
		VarIntLS[] x_room;		
		
		Sum objMatch;
		
		// solution store
		ArrayList<ArrayList<Integer>> result;
		
		Random R;
		
		public Schedule(ArrayList<ArrayList<Integer>> scheduledata, int nbHostProfessor, 
						int nbGuestProfessor, int nbSlot,int nbRoom, int[][] hostProfessorScore, 
						int[][] guestProfessorScore ) {
			// TODO Auto-generated constructor stub
			this.scheduledata = scheduledata;
			this.nbCommittee = scheduledata.size();
			this.nbHostProfessor = nbHostProfessor;
			this.nbGuestProfessor = nbGuestProfessor;
			this.nbSlot = nbSlot;
			this.nbRoom = nbRoom;
			this.hostProfessorScore = new int[nbCommittee][nbHostProfessor];
			this.guestProfessorScore = new int[nbCommittee][nbGuestProfessor];
			for(int i=0; i<nbCommittee; i++){
				for(int j=0; j<nbHostProfessor; j++){
					this.hostProfessorScore[i][j] = hostProfessorScore[i][j];
				}
				for(int j=0; j<nbGuestProfessor; j++){
					this.guestProfessorScore[i][j] = guestProfessorScore[i][j];
				}
			}
			
		}		
		
		
		public void stateModel(){
			ls = new LocalSearchManager();
			S = new ConstraintSystem(ls);
		
			x_supervisor = new int[nbCommittee];
			x_examiner1 = new VarIntLS[nbCommittee];
			x_examiner2 = new VarIntLS[nbCommittee];
			x_chairman = new VarIntLS[nbCommittee];
			x_secretary = new VarIntLS[nbCommittee];
			x_additionalmember = new VarIntLS[nbCommittee];
			x_slot = new VarIntLS[nbCommittee];
			x_room = new VarIntLS[nbCommittee];		
			
			IFunction[][] mf;
			
			
			for(int i = 0; i < nbCommittee; i++){
				ArrayList<Integer> e = scheduledata.get(i);
				int sp = e.get(0);
				int ex1 = e.get(1);
				int ex2 = e.get(2);
				int ch = e.get(3);
				int se = e.get(4);
				int am = e.get(5);
				int sl = e.get(6);
				int rm = e.get(7);
				
				x_supervisor[i] = sp; 
				if(ex1 == -1){
					x_examiner1[i] = new VarIntLS(ls, 0, nbGuestProfessor - 1);
				}else{
					x_examiner1[i] = new VarIntLS(ls, ex1, ex1);
				}
				
				if(ex2 == -1){
					x_examiner2[i] = new VarIntLS(ls, 0, nbHostProfessor - 1);
				}else{
					x_examiner2[i] = new VarIntLS(ls, ex2, ex2);
				}
				
				if(ch == -1){
					x_chairman[i] = new VarIntLS(ls, 0, nbHostProfessor - 1);
				}else{
					x_chairman[i] = new VarIntLS(ls, ch, ch);
				}
				
				if(se == -1){
					x_secretary[i] = new VarIntLS(ls, 0, nbHostProfessor - 1);
				}else{
					x_secretary[i] = new VarIntLS(ls, se, se);
				}
				
				if(am == -1){
					x_additionalmember[i] = new VarIntLS(ls, 0, nbGuestProfessor - 1);
				}else{
					x_additionalmember[i] = new VarIntLS(ls, am, am);
				}
				
				if(sl == -1){
					x_slot[i] = new VarIntLS(ls, 0, nbSlot - 1);
				}else{
					x_slot[i] = new VarIntLS(ls, sl, sl);
				}
				
				if(rm == -1){
					x_room[i] = new VarIntLS(ls, 0, nbRoom - 1);
				}else{
					x_room[i] = new VarIntLS(ls, rm, rm);
				}
				
			}
			
			mf = new IFunction[nbCommittee][2];
			for(int i = 0; i < nbCommittee; i++){
				mf[i][0] = new Element(guestProfessorScore,i,x_examiner1[i]);
				mf[i][1] = new Element(hostProfessorScore,i,x_examiner2[i]);
			}

			IFunction[] f = new IFunction[nbCommittee*2];
			int idx = -1;
			for(int i = 0; i < nbCommittee; i++)
				for(int j = 0; j < 2; j++){
					idx++;
					f[idx] = mf[i][j];
				}
			objMatch = new Sum(f);
			
			for(int i = 0; i < nbCommittee; i++){		
				
				S.post(new NotEqual(x_examiner2[i],x_supervisor[i]));
				S.post(new NotEqual(x_chairman[i],x_supervisor[i]));
				S.post(new NotEqual(x_secretary[i],x_supervisor[i]));
				
				S.post(new NotEqual(x_examiner1[i],x_additionalmember[i]));
				S.post(new NotEqual(x_examiner2[i],x_chairman[i]));
				S.post(new NotEqual(x_examiner2[i],x_secretary[i]));
				S.post(new NotEqual(x_chairman[i],x_secretary[i]));	
			}
			for(int i = 0; i < nbCommittee - 1; i++){		
				for(int j = i+1; j < nbCommittee; j++){
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_room[i],x_room[j])
						   ));
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_examiner1[i],x_examiner1[j])
						   ));	
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_examiner1[i],x_additionalmember[j])
						   ));	
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_examiner1[j],x_additionalmember[i])
						   ));	
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_additionalmember[i],x_additionalmember[j])
						   ));	
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_examiner2[i],x_examiner2[j])
						   ));	
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_examiner2[i],x_chairman[j])
						   ));	
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_examiner2[i],x_secretary[j])
						   ));
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_chairman[i],x_examiner2[j])
						   ));	
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_chairman[i],x_chairman[j])
						   ));	
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_chairman[i],x_secretary[j])
						   ));
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_secretary[i],x_examiner2[j])
						   ));	
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_secretary[i],x_chairman[j])
						   ));	
					S.post(new Implicate(
							 new IsEqual(x_slot[i],x_slot[j]), 
							 new NotEqual(x_secretary[i],x_secretary[j])
						   ));					
				}
			}			
			ls.close();
		}
		
		public void search() {
			TabuSearch ts = new TabuSearch();
			ts.search(S, 30, 20, 10000, 50);
			ts.searchMaintainConstraintsMaximize(objMatch, S, 30, 20, 10000, 50);
			//ts.searchMaintainConstraints(objMatch, S, 30, 20, 10000, 50);

			this.result = new ArrayList<ArrayList<Integer>>(); 
			for(int i = 0; i < nbCommittee; i++){
				ArrayList<Integer> e = new ArrayList<Integer>();
				e.add(x_examiner1[i].getValue());
				e.add(x_examiner2[i].getValue());
				e.add(x_chairman[i].getValue());
				e.add(x_secretary[i].getValue());
				e.add(x_additionalmember[i].getValue());				
				e.add(x_slot[i].getValue());
				e.add(x_room[i].getValue());
				result.add(e);
			}			
		}	
		
		public ArrayList<ArrayList<Integer>> getResult(){
			return result;
		}
	}