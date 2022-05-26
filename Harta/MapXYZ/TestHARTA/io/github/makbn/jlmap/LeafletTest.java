package io.github.makbn.jlmap;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import io.github.makbn.jlmap.model.JLLatLng;

import javax.swing.JButton;

import lombok.extern.log4j.Log4j2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.javafx.font.CompositeGlyphMapper;

public class LeafletTest {
	
	Leaflet l=new Leaflet();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test 
	public void testAudioFile(){
		
		String soundName =  "buzzer.wav";
		
		l.playSound(soundName);
	}

	@Test
	public void testApasareButon(){
		JButton b1=new JButton("Press");
		assertFalse(l.PressBut(b1));		
	}

	@Test
	public void testError() throws IOException{
		String url = "https://gps.sctpiasi.ro/json";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		int responseCode = con.getResponseCode();
		
		assertEquals(responseCode,200);
	}
	
}
