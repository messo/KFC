package hu.sch.kfc.client.ui.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Accessibility;
import com.google.gwt.user.client.ui.Widget;

public class Button extends Widget implements EventListener, HasClickHandlers {

    interface Style extends CssResource {
        String defaultState();

        String collapseRight();

        String collapseLeft();

        String leftCollapsedHoverCorrection();

        String leftCollapsedFocusCorrection();

        String onFocus();

        String onHover();

        String onClick();

        String disabled();
    }

    private static ButtonUiBinder uiBinder = GWT.create(ButtonUiBinder.class);

    interface ButtonUiBinder extends UiBinder<DivElement, Button> {
    }

    @UiField
    protected Style style;
    @UiField
    protected Element button;
    private boolean disabled = false;
    private boolean leftBorderCollapsed = false;

    public Button(String name, ClickHandler handler) {
        setElement(uiBinder.createAndBindUi(this));
        getElement().setAttribute("tabindex", "0");
        getElement().setAttribute("unselectable", "on");

        if (name != null)
            button.setInnerText(name);
        if (handler != null)
            addClickHandler(handler);

        Accessibility.setRole(getElement(), Accessibility.ROLE_BUTTON);
    }

    @Override
    protected void onAttach() {
        sinkEvents(Event.FOCUSEVENTS | Event.MOUSEEVENTS | Event.ONKEYPRESS);
        super.onAttach();
    }

    public Button(String name) {
        this(name, null);
    }

    public Button() {
        this(null, null);
    }

    public void setText(String name) {
        button.setInnerText(name);
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
        if (disabled) {
            addStyleName(style.disabled());
            getElement().setAttribute("tabindex", "-1");
        } else {
            removeStyleName(style.disabled());
            getElement().setAttribute("tabindex", "0");
        }
    }

    @Override
    public void onBrowserEvent(Event event) {
        if (!disabled) {
            int type = DOM.eventGetType(event);
            switch (type) {
            case Event.ONFOCUS:
                addStyleName(style.onFocus());
                if (leftBorderCollapsed) {
                    addStyleName(style.leftCollapsedFocusCorrection());
                }
                break;
            case Event.ONBLUR:
                removeStyleName(style.onFocus());
                removeStyleName(style.onClick());
                if (leftBorderCollapsed) {
                    removeStyleName(style.leftCollapsedFocusCorrection());
                }
                break;
            case Event.ONMOUSEOVER:
                addStyleName(style.onHover());
                if (leftBorderCollapsed) {
                    addStyleName(style.leftCollapsedHoverCorrection());
                }
                break;
            case Event.ONMOUSEOUT:
                removeStyleName(style.onHover());
                removeStyleName(style.onClick());
                if (leftBorderCollapsed) {
                    removeStyleName(style.leftCollapsedHoverCorrection());
                }
                break;
            case Event.ONMOUSEDOWN:
                addStyleName(style.onClick());
                break;
            case Event.ONMOUSEUP:
                removeStyleName(style.onClick());
                break;
            case Event.ONKEYPRESS:
                char keyCode = (char) DOM.eventGetKeyCode(event);
                if (keyCode == '\r' || keyCode == '\n') {
                    getElement().dispatchEvent(
                            Document.get().createClickEvent(1, 0, 0, 0, 0, false, false, false,
                                    false));
                }
                break;
            }

            super.onBrowserEvent(event);
        }
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

    public void collapse(Boolean left, Boolean right) {
        if (left != null) {
            if (left) {
                leftBorderCollapsed = true;
                addStyleName(style.collapseLeft());
            } else {
                leftBorderCollapsed = false;
                removeStyleName(style.collapseLeft());
            }
        }

        if (right != null) {
            if (right) {
                addStyleName(style.collapseRight());
            } else {
                removeStyleName(style.collapseRight());
            }
        }
    }
}
