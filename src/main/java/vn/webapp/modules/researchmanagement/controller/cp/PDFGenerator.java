package vn.webapp.modules.researchmanagement.controller.cp;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class PDFGenerator {
	/**
	 * Path to the font will be used
	 * /fonts/times.ttf
	 */
	private static final String _sPathToNormalTypeFontSource = "fonts/vi-fonts/times.ttf";
	public static final String _sHTMLTemplate = "html/completed.html";
	public static final String _sOutPutFile = "/results/content.pdf";
	
    /**
     * Creates a PDF content
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public void v_fCreatePdf(String file) throws IOException, DocumentException {
    	ClassLoader classLoader = getClass().getClassLoader();
    	File o_FontFile = new File(classLoader.getResource(PDFGenerator._sPathToNormalTypeFontSource).getFile());
    	String sz_FontPath = o_FontFile.getAbsolutePath();
    	
    	File o_TemplateFile = new File(classLoader.getResource(PDFGenerator._sHTMLTemplate).getFile());
    	String sz_TemplatePath = o_TemplateFile.getAbsolutePath();
    	
        // step 1
        Document document = new Document();
        try{
	        // step 2
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
	        // step 3
	        document.open();
	        // step 4
	        // CSS
	        CSSResolver cssResolver = new StyleAttrCSSResolver();
	        
	        // HTML
	        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	        fontProvider.register(sz_FontPath, "Serif");
	        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
	        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
	        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
	        
	        // Pipelines
	        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
	        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
	        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
	        
	        // XML Worker
	        XMLWorker worker = new XMLWorker(css, true);
	        XMLParser p = new XMLParser(worker);
	        p.parse(new FileInputStream(sz_TemplatePath), Charset.forName("UTF-8"));
	        // step 5
	        document.close();
    	}catch(IOException e)
    	{
    		e.printStackTrace();
    	}catch(DocumentException e)
    	{
    		e.printStackTrace();
    	}finally{
    		try {
                // Close the document regardless of what happens...
    			document.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
    	}
    }

    /**
     * PDF Generator 
     * @throws IOException
     * @throws DocumentException
     */
    public static void v_fGenerator(String sOutPutFile) throws IOException, DocumentException {
    	//Creating empty pdf file
    	Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(sOutPutFile));
		
        File file = new File(sOutPutFile);
        file.getParentFile().mkdirs();
        new PDFGenerator().v_fCreatePdf(sOutPutFile);
    }
}
