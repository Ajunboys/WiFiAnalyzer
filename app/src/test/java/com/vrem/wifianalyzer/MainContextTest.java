/*
 * WiFiAnalyzer
 * Copyright (C) 2018  VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.vrem.wifianalyzer;

import android.support.annotation.StyleRes;

import com.vrem.wifianalyzer.settings.Settings;
import com.vrem.wifianalyzer.settings.ThemeStyle;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainContextTest {

    @After
    public void tearDown() {
        MainContextHelper.INSTANCE.restore();
    }

    @Test
    public void testGetDefaultThemeWithNoSettings() throws Exception {
        // setup
        @StyleRes int expected = R.style.ThemeAppCompatDark;
        // execute
        @StyleRes int actual = MainContext.INSTANCE.getDefaultTheme();
        // validate
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDefaultThemeWithNoTheme() throws Exception {
        // setup
        Settings settings = MainContextHelper.INSTANCE.getSettings();
        when(settings.getThemeStyle()).thenReturn(null);
        @StyleRes int expected = R.style.ThemeAppCompatDark;
        // execute
        @StyleRes int actual = MainContext.INSTANCE.getDefaultTheme();
        // validate
        assertEquals(expected, actual);
        verify(settings).getThemeStyle();
    }

    @Test
    public void testGetDefaultTheme() throws Exception {
        // setup
        Settings settings = MainContextHelper.INSTANCE.getSettings();
        when(settings.getThemeStyle()).thenReturn(ThemeStyle.LIGHT);
        @StyleRes int expected = R.style.ThemeAppCompatLight;
        // execute
        @StyleRes int actual = MainContext.INSTANCE.getDefaultTheme();
        // validate
        assertEquals(expected, actual);
        verify(settings).getThemeStyle();
    }
}