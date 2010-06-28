package hu.sch.kfc.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class Shell extends Composite {

    private static ShellUiBinder uiBinder = GWT.create(ShellUiBinder.class);

    interface ShellUiBinder extends UiBinder<Widget, Shell> {
    }

    @UiField
    SimplePanel mainPanel;
    @UiField
    Button likeBtn;
    @UiField
    Label likeLabel;

    public Shell() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public SimplePanel getMainPanel() {
        return mainPanel;
    }

    @UiHandler("likeBtn")
    public void onLike(ClickEvent e) {
        RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, GWT.getModuleBaseURL()+ "channel?message=proba");
        //rb.setHeader("X-Atmosphere-Version", "0.6");
        //rb.setHeader("X-Atmosphere-Transport", "long-polling");
        rb.setCallback(new RequestCallback() {
            
            @Override
            public void onResponseReceived(Request arg0, Response arg1) {
            }
            
            @Override
            public void onError(Request arg0, Throwable arg1) {
            }
        });
        try {
            rb.send();
        } catch (RequestException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void setLikeLabel(int liked) {
        likeLabel.setText(String.valueOf(liked));
    } 
}
