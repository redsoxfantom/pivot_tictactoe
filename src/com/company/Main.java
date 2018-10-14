package com.company;

import com.company.window.MainMenu;
import com.company.window.WindowManager;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;

public class Main implements Application
{
    private WindowManager window = null;

    public static void main(String[] args)
    {
        DesktopApplicationContext.main(Main.class,args);
    }

    @Override
    public void startup(Display display, Map<String, String> map) throws Exception
    {
        BXMLSerializer ser = new BXMLSerializer();
        window = (WindowManager) ser.readObject(WindowManager.class,"mainwindow.bxml");
        window.open(display);
    }

    @Override
    public boolean shutdown(boolean b) throws Exception
    {
        if(window != null)
        {
            window.close();
        }

        return false;
    }

    @Override
    public void suspend() throws Exception
    {

    }

    @Override
    public void resume() throws Exception
    {

    }
}
