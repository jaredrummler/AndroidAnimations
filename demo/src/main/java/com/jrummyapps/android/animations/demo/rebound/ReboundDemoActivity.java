/*
 * Copyright (C) 2016 Jared Rummler <jared.rummler@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.jrummyapps.android.animations.demo.rebound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.jrummyapps.android.animations.demo.R;

public class ReboundDemoActivity extends AppCompatActivity {

  private final BaseSpringSystem springSystem = SpringSystem.create();
  private final ExampleSpringListener springListener = new ExampleSpringListener();
  private FrameLayout rootView;
  private Spring scaleSpring;
  private View imageView;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_rebound_demo);
    rootView = (FrameLayout) findViewById(R.id.root_view);
    imageView = rootView.findViewById(R.id.image_view);

    // Create the animation spring.
    scaleSpring = springSystem.createSpring();

    // Add an OnTouchListener to the root view.
    rootView.setOnTouchListener(new View.OnTouchListener() {

      @Override public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN:
            // When pressed start solving the spring to 1.
            scaleSpring.setEndValue(1);
            break;
          case MotionEvent.ACTION_UP:
          case MotionEvent.ACTION_CANCEL:
            // When released start solving the spring to 0.
            scaleSpring.setEndValue(0);
            break;
        }
        return true;
      }
    });
  }

  @Override public void onResume() {
    super.onResume();
    // Add a listener to the spring when the Activity resumes.
    scaleSpring.addListener(springListener);
  }

  @Override public void onPause() {
    super.onPause();
    // Remove the listener to the spring when the Activity pauses.
    scaleSpring.removeListener(springListener);
  }

  private class ExampleSpringListener extends SimpleSpringListener {

    @Override public void onSpringUpdate(Spring spring) {
      // On each update of the spring value, we adjust the scale of the image view to match the
      // springs new value. We use the SpringUtil linear interpolation function mapValueFromRangeToRange
      // to translate the spring's 0 to 1 scale to a 100% to 50% scale range and apply that to the View
      // with setScaleX/Y. Note that rendering is an implementation detail of the application and not
      // Rebound itself. If you need Gingerbread compatibility consider using NineOldAndroids to update
      // your view properties in a backwards compatible manner.
      float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, 0.5);
      imageView.setScaleX(mappedValue);
      imageView.setScaleY(mappedValue);
    }
  }

}
