package org.tss.engim;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class NavMenu extends Panel
{
  public NavMenu(String id)
  {
    super(id);
    
    BookmarkablePageLink home = 
      new BookmarkablePageLink("home", 
        HomePage.class);
    add(home);
    
    BookmarkablePageLink merci = 
      new BookmarkablePageLink("merci", 
        PaginaMerci.class);
    add(merci);
    
    BookmarkablePageLink costi = 
      new BookmarkablePageLink("costi", 
        PaginaCosti.class);
    add(costi);

    BookmarkablePageLink spedizioni = 
      new BookmarkablePageLink("spedizioni", 
        PaginaSpedizioni.class);
    add(spedizioni);
    
   
    
    
  }
  
}
