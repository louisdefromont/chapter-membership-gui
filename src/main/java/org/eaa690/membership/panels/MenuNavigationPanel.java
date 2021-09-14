package org.eaa690.membership.panels;

import javax.swing.JPanel;

import org.eaa690.membership.ApplicationFrame;

public class MenuNavigationPanel extends JPanel {

    public MenuNavigationPanel(ApplicationFrame applicationFrame, MenuPanel[] childMenus, MenuPanel parentMenu) {
        if (parentMenu != null) {
            MenuNavigationButton returnButton = new MenuNavigationButton(String.format("Return to: %s", parentMenu.getTitle()), parentMenu, applicationFrame);
            add(returnButton);
        }
        if (childMenus != null) {
            for (MenuPanel childMenu : childMenus) {
                MenuNavigationButton childNavigationButton = new MenuNavigationButton(childMenu.getTitle(), childMenu, applicationFrame);
                add(childNavigationButton);
            }
        }
    }
}
