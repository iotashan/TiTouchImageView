/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package org.iotashan.TiTouchImageView;

import java.io.IOException;
import java.util.HashMap;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.AsyncResult;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.kroll.common.TiMessenger;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiBlob;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiUIHelper;
import org.appcelerator.titanium.io.TiBaseFile;
import org.appcelerator.titanium.io.TiFileFactory;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiDrawableReference;
import org.appcelerator.titanium.view.TiUIView;

import com.ortiz.touch.TouchImageView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.PointF;
import android.os.Message;


@Kroll.proxy(creatableInModule=TiTouchImageViewModule.class, propertyAccessors = { "zoom", "image", "maxZoom", "minZoom" })
public class ViewProxy extends TiViewProxy
{
	// Standard Debugging variables
	private static final String LCAT = "TiTouchImageView";
	private static final boolean DBG = TiConfig.LOGD;

	private static final int MSG_FIRST_ID = TiViewProxy.MSG_LAST_ID + 1;
	public static final int MSG_RESET_ZOOM = MSG_FIRST_ID + 101;
	public static final int MSG_SCROLL_TO = MSG_FIRST_ID + 102;

	private class TiTouchImageView extends TiUIView
	{
		TouchImageView tiv;

		public TiTouchImageView(final TiViewProxy proxy) {
			super(proxy);

			tiv = new TouchImageView(proxy.getActivity());

			getLayoutParams().autoFillsHeight = true;
			getLayoutParams().autoFillsWidth = true;

			setNativeView(tiv);
		}

		@Override
		public void processProperties(KrollDict props)
		{
			super.processProperties(props);

			if (props.containsKey("zoom")) {
				tiv.setZoom(TiConvert.toFloat(proxy.getProperty("zoom")));
			}
			if (props.containsKey("image")) {
				handleImage(proxy.getProperty("image"));
			}
			if (props.containsKey("maxZoom")) {
				tiv.setMaxZoom(TiConvert.toFloat(proxy.getProperty("maxZoom")));
			}
			if (props.containsKey("minZoom")) {
				tiv.setMinZoom(TiConvert.toFloat(proxy.getProperty("minZoom")));
			}
		}

		@Override
		public void propertyChanged(String key, Object oldValue, Object newValue, KrollProxy proxy)
		{
			if (key.equals("zoom")) {
				tiv.setZoom(TiConvert.toFloat(newValue));
			}
			if (key.equals("image")) {
				handleImage(newValue);
			}
			if (key.equals("maxZoom")) {
				tiv.setMaxZoom(TiConvert.toFloat(newValue));
			}
			if (key.equals("minZoom")) {
				tiv.setMinZoom(TiConvert.toFloat(newValue));
			}
		}

		public void setScrollPosition(float x, float y)
		{
			tiv.setScrollPosition(x,y);
		}

		public void resetZoom()
		{
			tiv.resetZoom();
		}

        public float getCurrentZoom()
        {
            return tiv.getCurrentZoom();
        }

        public PointF getScrollPosition()
        {
            return tiv.getScrollPosition();
        }

		private Bitmap loadImageFromApplication(String imageName) {
			Bitmap result = null;
			try {
				// Load the image from the application assets
				String url = getPathToApplicationAsset(imageName);
				TiBaseFile file = TiFileFactory.createTitaniumFile(new String[] { url }, false);
				result = TiUIHelper.createBitmap(file.getInputStream());
			} catch (IOException e) {
				Log.e(LCAT, " TiTouchImageView only supports local image files");
			}
			return result;
		}

		private void handleImage(Object val)
		{
			if (val instanceof TiBlob) {
				// this is a blob, parse accordingly
				TiBlob imgBlob = (TiBlob)val;
				TiDrawableReference ref = TiDrawableReference.fromBlob(proxy.getActivity(), imgBlob);
				tiv.setImageBitmap(ref.getBitmap());
			} else {
				String imgValue = (String)val;
				tiv.setImageBitmap(loadImageFromApplication(imgValue));
			}
		}

		private String getPathToApplicationAsset(String assetName) {
			// The url for an application asset can be created by resolving the specified
			// path with the proxy context. This locates a resource relative to the
			// application resources folder
			String result = resolveUrl(null, assetName);

			return result;
		}

        public void recycleBitmap ()
        {
            ((BitmapDrawable)tiv.getDrawable()).getBitmap().recycle();
        }
	}


	// Constructor
	public ViewProxy()
	{
		super();
	}

	@Override
	public TiUIView createView(Activity activity)
	{
		return new TiTouchImageView(this);
	}

	protected TiTouchImageView getView()
	{
		return (TiTouchImageView) getOrCreateView();
	}

	public boolean handleMessage(Message msg)
	{
		boolean handled = false;

		switch(msg.what) {
			case MSG_RESET_ZOOM:
				getView().resetZoom();
				handled = true;
				break;
			case MSG_SCROLL_TO:
				handleScrollTo(msg.arg1, msg.arg2);
				AsyncResult result = (AsyncResult) msg.obj;
				result.setResult(null); // signal scrolled
				handled = true;
				break;
			default:
				handled = super.handleMessage(msg);
		}

		return handled;
	}

	// Methods
	@Kroll.method
	public void resetZoom()
	{
		getMainHandler().removeMessages(MSG_RESET_ZOOM);
		getMainHandler().sendEmptyMessage(MSG_RESET_ZOOM);
	}

	@Kroll.method
	public void scrollTo(float x, float y) {
		if (!TiApplication.isUIThread()) {
			TiMessenger.sendBlockingMainMessage(getMainHandler().obtainMessage(MSG_SCROLL_TO, (int)x, (int)y), getActivity());
		} else {
			handleScrollTo(x,y);
		}
	}

	private void handleScrollTo(float x, float y) {
		getView().setScrollPosition(x,y);
	}

  @Kroll.method
  public float getCurrentZoom() {
      return getView().getCurrentZoom();
  }

  @Kroll.method
  public HashMap getScrollPosition() {
      PointF point = getView().getScrollPosition();
      HashMap result = new HashMap();
      result.put("x", point.x);
      result.put("y", point.y);
      return result;
  }

  @Kroll.method
  public void recycleBitmap() {
      getView().recycleBitmap ();
  }
}
