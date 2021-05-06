package com.user.p0791_xmlpullparser;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String tmp = "";
        try{
            XmlPullParser xpp = prepareXpp2();

            while(xpp.getEventType() != XmlPullParser.END_DOCUMENT){
                switch (xpp.getEventType()){
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(LOG_TAG,"Start document");
                        break;
                    case XmlPullParser.START_TAG:
                        Log.d(LOG_TAG, "Start tag: name = " + xpp.getName()
                            + ", depth = " + xpp.getDepth() + ", attrCount = " +
                            xpp.getAttributeCount());
                        tmp = "";
                        for(int i = 0; i < xpp.getAttributeCount(); i++){
                            tmp = tmp + xpp.getAttributeName(i) + " = "
                                    + xpp.getAttributeValue(i) + ", ";
                        }
                        if(!TextUtils.isEmpty(tmp)){
                            Log.d(LOG_TAG,"Attributes: " + tmp);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        Log.d(LOG_TAG, "END_TAG: name = " + xpp.getName());
                        break;
                    case XmlPullParser.TEXT:
                        Log.d(LOG_TAG, "text = " + xpp.getText());
                        break;
                    default:
                        break;
                }
                xpp.next();
            }
            Log.d(LOG_TAG,"END_DOCUMENT");
        } catch(XmlPullParserException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    XmlPullParser prepareXpp(){
        return getResources().getXml(R.xml.data);
    }

    XmlPullParser prepareXpp2() throws XmlPullParserException{
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new StringReader("<data><phone><company>Samsung</company></phone></data>"));
        return xpp;
    }
}
