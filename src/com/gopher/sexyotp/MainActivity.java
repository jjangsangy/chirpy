package com.gopher.sexyotp;

import java.io.*;
import java.util.*;

import twitter4j.*;
import twitter4j.conf.*;
import android.app.*;
import android.content.*;
import android.hardware.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
	TextView mTextView;
	TextView msgTextView;

	SensorManager mSensorManager;
	Sensor mSensor;
	float magic_num;
	int keygenCount = 0;
	String intKey = "";
	byte[] key;
	final int KEY_LENGTH = 140;
	Button generateButton, sendMsgButton;
	String msg = "";
	private Twitter mTwitter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		key = new byte[KEY_LENGTH];

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

		mTextView = (TextView) findViewById(R.id.mainTextView);
		mTextView.setText("");
		msgTextView = (TextView) findViewById(R.id.msgTextInput);

		generateButton = (Button) findViewById(R.id.genButton);
		sendMsgButton = (Button) findViewById(R.id.sendButton);
		sendMsgButton.setEnabled(false);

		mTwitter = getTwitter();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

	}

	private Twitter getTwitter() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("X9GYfyMdz7kS1hWPSbDpYfVJx");
		cb.setOAuthConsumerSecret("FC1b4Ig0lIb5TvWSM4daRp7xwGOlkJ2XNz7e6T396VwepAPcUX");
		cb.setOAuthAccessToken("1025950273-HujNuw7y8GrHThM9ywIuwzJIYUkXMc2Ys8TWVCa");
		cb.setOAuthAccessTokenSecret("pasMIpJC7dUPSmEHtRuRJAsWOM7XLywN5NI7ShzYtdep9");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		return twitter;
	}

	public void sendTweet(View v) {
		msg = "" + msgTextView.getText();
		String encrypted = encrypt(msg, Arrays.copyOfRange(key, 3, key.length));
		String decrypted = decrypt(encrypted, Arrays.copyOfRange(key, 3, key.length));

		System.out.println("Original message: " + msg);
		System.out.println("Encrypted message: " + encrypted);
		System.out.println("Decrypted message: " + decrypted);

		String tweetUrl = "https://twitter.com/intent/tweet?text="
				+ new String(encrypted + "%20#chirp%20#" + (int) key[0] + (int) key[1] + (int) key[2]);

		Uri.encode(tweetUrl);
		Uri uri = Uri.parse(Uri.encode(tweetUrl));
		System.out.println(uri);
		startActivity(new Intent(Intent.ACTION_VIEW, uri));
	}

	public void receiveTweet(View v) {
		ArrayList<String> tweetsArray = new ArrayList<String>();

		Twitter twitter = mTwitter;
		Query query = new Query("#chirpy #" + (int) key[0] + (int) key[1] + (int) key[2]);
		query.setResultType(Query.MIXED);
		query.setCount(30);
		QueryResult result;

		try {
			result = twitter.search(query);

			for (Status tweet : result.getTweets()) {
				// Log.d("Wisdom", tweet.getUser() + ":" + tweet.getText());
				// tweetsArray.add(tweet.getText());
				System.out.println(tweet.getText());
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateKey(View v) {
		mTextView.setText("SHAKE DEVICE");
		generateButton.setText("Generating key...");
		mSensorManager.registerListener(sel, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
	}

	/*
	 * protected void onStart() { // TODO Auto-generated method stub super.onStart();
	 * 
	 * if (keygenCount >= KEY_LENGTH) { mSensorManager.unregisterListener(sel); mTextView.setText(key); } }
	 */
	public void printKey() {
		mSensorManager.unregisterListener(sel);
		mTextView.setText("Key generated.");
		generateButton.setText("Generate Key");

		// System.out.println("Byte array length: " + key.length);
		sendMsgButton.setEnabled(true);
		// System.out.println(key[0]);
	}

	SensorEventListener sel = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			String x, y, z;
			String numX1, numX2;
			String numY1, numY2, numY3;
			String numZ1, numZ2, numZ3;
			byte b = 0;
			boolean reset = false;

			x = Float.toString(Math.abs(event.values[0]));
			y = Float.toString(Math.abs(event.values[1]));
			z = Float.toString(Math.abs(event.values[2]));

			// System.out.println(x + " " + y + " " + z);

			numX1 = x.substring(x.length() - 2, x.length() - 1);
			numX2 = x.substring(x.length() - 1, x.length());

			if (numX1.contains(".") || numX2.contains("."))
				reset = true;
			else {
				if (Integer.parseInt(numX1) % 2 == 1)
					b = (byte) (b | (1 << 0));
				else
					b = (byte) (b | (0 << 0));

				if (Integer.parseInt(numX2) % 2 == 1)
					b = (byte) (b | (1 << 1));
				else
					b = (byte) (b | (0 << 1));
			}

			numY1 = y.substring(y.length() - 3, y.length() - 2);
			numY2 = y.substring(y.length() - 2, y.length() - 1);
			numY3 = y.substring(y.length() - 1, y.length());

			if (numY1.contains(".") || numY2.contains(".") || numY3.contains("."))
				reset = true;
			else {
				if (Integer.parseInt(numY1) % 2 == 1)
					b = (byte) (b | (1 << 2));
				else
					b = (byte) (b | (0 << 2));

				if (Integer.parseInt(numY2) % 2 == 1)
					b = (byte) (b | (1 << 3));
				else
					b = (byte) (b | (0 << 3));

				if (Integer.parseInt(numY3) % 2 == 1)
					b = (byte) (b | (1 << 4));
				else
					b = (byte) (b | (0 << 4));
			}

			numZ1 = z.substring(z.length() - 3, z.length() - 2);
			numZ2 = z.substring(z.length() - 2, z.length() - 1);
			numZ3 = z.substring(z.length() - 1, z.length());

			if (numZ1.contains(".") || numZ2.contains(".") || numZ3.contains("."))
				reset = true;
			else {
				if (Integer.parseInt(numZ1) % 2 == 1)
					b = (byte) (b | (1 << 5));
				else
					b = (byte) (b | (0 << 5));

				if (Integer.parseInt(numZ2) % 2 == 1)
					b = (byte) (b | (1 << 6));
				else
					b = (byte) (b | (0 << 6));

				if (Integer.parseInt(numZ3) % 2 == 1)
					b = (byte) (b | (1 << 7));
				else
					b = (byte) (b | (0 << 7));
			}

			if (keygenCount < KEY_LENGTH) {
				if (!reset) {
					key[keygenCount] = b;
					keygenCount++;
				}
				// System.out.println("Key changed.");
			} else {
				// System.out.println(numX1 + " " + numX2 + " " + numY1 + " " + numY2 + " " + numY3 + " " + numZ1 + " "
				// + numZ2 + " " + numZ3);

				// System.out.println(b);
				printKey();
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}
	};

	public static String encrypt(String message, byte[] key) {
		try {
			byte[] byteMessage = message.getBytes("US-ASCII");
			for (int i = 0; i < byteMessage.length; i++) {
				byteMessage[i] = (byte) (byteMessage[i] ^ key[i]);
			}

			return Base64.encodeToString(byteMessage, 0);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String tweet, byte[] key) {
		byte[] decodedTweet = Base64.decode(tweet, 0);
		for (int i = 0; i < decodedTweet.length; i++) {
			decodedTweet[i] = (byte) (decodedTweet[i] ^ key[i]);
		}
		String decryptedMessage;
		try {
			decryptedMessage = new String(decodedTweet, "US-ASCII");
			return decryptedMessage;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
