package org.tss.engim;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.tss.engim.dao.DAOGenerico;
import org.tss.engim.db.ChiavePrimaria;


public class AzioniPanel extends Panel
{
  
  public AzioniPanel(String id, final ChiavePrimaria p)
  {
    super(id);
    
    add(new AjaxLink("elimina")
    {
      @Override
      public void onClick(AjaxRequestTarget art)
      {
        DAOGenerico.elimina(p);
        throw new RestartResponseException(getPage());
      }   
    });

  }
  
}
