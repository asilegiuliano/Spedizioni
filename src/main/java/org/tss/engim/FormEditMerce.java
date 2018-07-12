package org.tss.engim;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.tss.engim.dao.DAOGenerico;
import org.tss.engim.db.Merce;


public class FormEditMerce extends Form<Merce>
{
  private Merce m;

  public FormEditMerce(String id)
  {
    super(id);
    add(new FeedbackPanel("feedback"));
        
    // add(new TextField("codice"));
    TextField codice = new TextField("codice");
    codice.setRequired(true);
    add(codice);
    
    // add(new TextField("descrizione"));
    TextField descrizione = new TextField("descrizione");
    descrizione.setRequired(true);
    add(descrizione);
    
    // add(new TextField("peso"));
    TextField<Float> peso = new TextField("peso");
    peso.setRequired(true);
    add(peso);
    
  }

  @Override
  protected void onBeforeRender()
  {
    // ogni volta che wicket rivisualizza la pagina io creo un nuovo oggetto da visualizzare (per la cache)
    // (spostate da sopra)
    m = new Merce();
    setDefaultModel(new CompoundPropertyModel<Merce>(m));
    
    super.onBeforeRender(); // viene richiamato da wicket appena prima del render dell'html (non sulla costruzione dell'oggetto)
  }
  
  

  @Override
  protected void onSubmit()
  {
      DAOGenerico.inserisci_o_aggiorna(m);
      m.setId(null);
  }
  
}