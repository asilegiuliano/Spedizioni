package org.tss.engim;

import java.math.BigDecimal;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.tss.engim.dao.DAOGenerico;
import org.tss.engim.db.Merce;



public class FormEditMerce extends Form<Merce>
{
  private Merce m;

  public FormEditMerce(String id)
  {
    super(id);

    // add(new TextField("codice"));
    TextField codice = new TextField("codice");
    codice.setRequired(true);
    add(codice);
    
    // add(new TextField("descrizione"));
    TextField descrizione = new TextField("descrizione");
    descrizione.setRequired(true);
    add(descrizione);
    
    // add(new TextField("peso"));
    NumberTextField peso = new NumberTextField("peso");
    // indichiamo lo step, tra uno e l'altro
    peso.setStep(0.1);
    peso.setRequired(true);
    // validator -> classe che dice se l'indice è valido o no
    // per le mail -> new EmailValidator 
    peso.add(new RangeValidator<>(BigDecimal.valueOf(0.1), BigDecimal.valueOf(100000)));
    // modificare gli attributi html tramite wicket ->
    // peso.add(new AttributeModifier("type", "number"));
    add(peso);
    
    add(new FeedbackPanel("feedback"));
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