package edu.cmu.deiis.annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.AnswerScore;

public class AnswerScoreAnnotator extends JCasAnnotator_ImplBase{
  
  public void process(JCas aJCas) {
    String docText = aJCas.getDocumentText();
    int current_index = 0; 
    while(!docText.isEmpty())
    {
      if(!docText.startsWith("A "))
      {
        current_index += docText.indexOf("\n")+1;
        docText = docText.substring(docText.indexOf("\n")+1);        
      }
      else
      {
        AnswerScore annotation = new AnswerScore(aJCas);
        annotation.setBegin(current_index+4);
        current_index += docText.indexOf("\n")+1;
        docText = docText.substring(docText.indexOf("\n")+1); 
        annotation.setEnd(current_index-1);
        annotation.setConfidence(1);
        annotation.setCasProcessorId("cmu.edu.deiis.annotators.AnswerScoreAnnotator");
        

        annotation.addToIndexes();
      }
    }    
    
  }
}



