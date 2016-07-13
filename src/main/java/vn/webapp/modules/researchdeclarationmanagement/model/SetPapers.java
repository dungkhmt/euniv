package vn.webapp.modules.researchdeclarationmanagement.model;

import java.util.Set;
public class SetPapers{
	
	private String paperAuthorsName;
	private String paperPublicName;
    private String paperJournalConferencecName;
    private String paperVolumn;
    private String paperISSN;
    private String paperGeneralBudget;
    private int paperTotalNumberOfAuthors;
    private int paperTotalNumberOfInternalAuthors;
    private int paperBudgetOfInternalAuthors;
    private int paperBudgetForOneAuthor;
    private Set<SetPaperStaffs> paperStaffs;
    
	public String getPaperAuthorsName() {
		return paperAuthorsName;
	}
	public void setPaperAuthorsName(String paperAuthorsName) {
		this.paperAuthorsName = paperAuthorsName;
	}
	public String getPaperPublicName() {
		return paperPublicName;
	}
	public void setPaperPublicName(String paperPublicName) {
		this.paperPublicName = paperPublicName;
	}
	public String getPaperJournalConferencecName() {
		return paperJournalConferencecName;
	}
	public void setPaperJournalConferencecName(String paperJournalConferencecName) {
		this.paperJournalConferencecName = paperJournalConferencecName;
	}
	public String getPaperVolumn() {
		return paperVolumn;
	}
	public void setPaperVolumn(String paperVolumn) {
		this.paperVolumn = paperVolumn;
	}
	public String getPaperISSN() {
		return paperISSN;
	}
	public void setPaperISSN(String paperISSN) {
		this.paperISSN = paperISSN;
	}
	public String getPaperGeneralBudget() {
		return paperGeneralBudget;
	}
	public void setPaperGeneralBudget(String paperGeneralBudget) {
		this.paperGeneralBudget = paperGeneralBudget;
	}
	public int getPaperTotalNumberOfAuthors() {
		return paperTotalNumberOfAuthors;
	}
	public void setPaperTotalNumberOfAuthors(int paperTotalNumberOfAuthors) {
		this.paperTotalNumberOfAuthors = paperTotalNumberOfAuthors;
	}
	public int getPaperTotalNumberOfInternalAuthors() {
		return paperTotalNumberOfInternalAuthors;
	}
	public void setPaperTotalNumberOfInternalAuthors(
			int paperTotalNumberOfInternalAuthors) {
		this.paperTotalNumberOfInternalAuthors = paperTotalNumberOfInternalAuthors;
	}
	public int getPaperBudgetForOneAuthor() {
		return paperBudgetForOneAuthor;
	}
	public void setPaperBudgetForOneAuthor(int paperBudgetForOneAuthor) {
		this.paperBudgetForOneAuthor = paperBudgetForOneAuthor;
	}
	public Set<SetPaperStaffs> getPaperStaffs() {
		return paperStaffs;
	}
	public void setPaperStaffs(Set<SetPaperStaffs> paperStaffs) {
		this.paperStaffs = paperStaffs;
	}
	public int getPaperBudgetOfInternalAuthors() {
		return paperBudgetOfInternalAuthors;
	}
	public void setPaperBudgetOfInternalAuthors(int paperBudgetOfInternalAuthors) {
		this.paperBudgetOfInternalAuthors = paperBudgetOfInternalAuthors;
	}
}
