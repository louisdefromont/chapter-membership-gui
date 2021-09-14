package org.eaa690.membership.panels;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import javax.swing.JPanel;

import org.eaa690.membership.ApplicationFrame;

import lombok.Getter;

@Getter
public abstract class MenuPanel extends JPanel {
    private MenuNavigationPanel menuNavigationPanel;
    private String title;
    @Getter
    private MenuPanel parentMenu;

    public MenuPanel(ApplicationFrame applicationFrame, MenuPanel parentMenu, BiFunction<ApplicationFrame, MenuPanel, MenuPanel>[] childMenuConstructors, String title) {
        this.title = title;
        this.parentMenu = parentMenu;
        MenuPanel[] childMenus;
        if (childMenuConstructors == null) {
            childMenus = null;
        } else {
            childMenus = new MenuPanel[childMenuConstructors.length];
            for (int i = 0; i < childMenus.length; i++) {
                childMenus[i] = childMenuConstructors[i].apply(applicationFrame, this);
            }
        }
        menuNavigationPanel = new MenuNavigationPanel(applicationFrame, childMenus, this.getParentMenu());
    }

    public abstract void displayPanel();
}
