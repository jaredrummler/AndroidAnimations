/*
 * Copyright (C) 2017 Jared Rummler
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
 */

package com.jaredrummler.android.animations;

import android.support.annotation.NonNull;
import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.jaredrummler.android.listeners.TouchReleaseListener;

import java.lang.ref.WeakReference;

/**
 * Simplify animating views with Facebook's <a href="http://facebook.github.io/rebound/">Rebound</a>
 */
public class Rebound {

  private static volatile Rebound singleton;

  public static Rebound getSingleton() {
    if (singleton == null) {
      synchronized (Rebound.class) {
        if (singleton == null) {
          singleton = new Rebound();
        }
      }
    }
    return singleton;
  }

  /**
   * Create a springy animation.
   *
   * @param endValue
   *     the endValue for the {@link Spring}
   * @param views
   *     the views to animate
   * @return the spring for chaining
   * @see #setEndValue(double, View...)
   */
  public static Spring animate(double endValue, @NonNull View... views) {
    return getSingleton().setEndValue(endValue, views);
  }

  private final Spring spring;
  private WeakReference<View[]> views;

  private Rebound() {
    spring = SpringSystem.create().createSpring();
    spring.addListener(new SimpleSpringListener() {

      @Override public void onSpringUpdate(Spring spring) {
        View[] targets = views.get();
        if (targets != null && targets.length > 0) {
          float value = (float) spring.getCurrentValue();
          float scale = 1f - (value * 0.5f);
          for (View view : targets) {
            view.setScaleX(scale);
            view.setScaleY(scale);
          }
        }
      }
    });
  }

  /**
   * Set the rest value to determine the displacement for the spring
   *
   * @param endValue
   *     the endValue for the spring
   * @param views
   *     the views to set the endValue on
   * @return the spring for chaining
   */
  public Spring setEndValue(double endValue, @NonNull View... views) {
    this.views = new WeakReference<>(views);
    return spring.setEndValue(endValue);
  }

  /**
   * Helper to easily animate a view when clicked.
   *
   * <p>Usage:</p>
   *
   * <pre>
   * view.setOnTouchListener(new Rebound.SpringyTouchListener() {
   *
   *     &#064;Override public void onClick(View v) {
   *
   *     }
   * });
   * </pre>
   */
  public abstract static class SpringyTouchListener extends TouchReleaseListener {

    private double value = 0.25;

    public SpringyTouchListener setEndValue(double value) {
      this.value = value;
      return this;
    }

    @Override public void onTouched(View v) {
      animate(value, v);
    }

    @Override public void onReleased(View v, boolean clicked) {
      animate(0, v);
      if (clicked) {
        onClick(v);
      }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v
     *     The view that was clicked.
     */
    public abstract void onClick(View v);

  }

}
