package com.aszostek.SimpleSimon.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.aszostek.SimpleSimon.SimpleSimon;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(1980, 1080);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new SimpleSimon();
        }
}