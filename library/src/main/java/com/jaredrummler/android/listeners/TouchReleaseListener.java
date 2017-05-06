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

package com.jaredrummler.android.listeners;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import static android.view.MotionEvent.ACTION_CANCEL;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_UP;

/**
 * Implements {@link View.OnTouchListener} and listens for the following events:
 *
 * <ul>
 * <li>{@link MotionEvent#ACTION_CANCEL}</li>
 * <li>{@link MotionEvent#ACTION_DOWN}</li>
 * <li>{@link MotionEvent#ACTION_UP}</li>
 * </ul>
 *
 * <p>When {@link MotionEvent#ACTION_UP} is invoked it checks if the user's finger is still on the
 * view.</p>
 */
public abstract class TouchReleaseListener implements View.OnTouchListener {

  private Rect rect;

  /**
   * Called when the view is pressed.
   *
   * @param v
   *     The {@link View}
   */
  public abstract void onTouched(View v);

  /**
   * @param v
   *     The {@link View}
   * @param clicked
   *     {@code true} if the view was "clicked", {@code false} if the gesture was cancelled or
   *     {@link MotionEvent#ACTION_DOWN} was released outside of the view.
   */
  public abstract void onReleased(View v, boolean clicked);

  @Override public boolean onTouch(View v, MotionEvent event) {
    if (v == null) {
      return false;
    }
    switch (event.getAction()) {
      case ACTION_DOWN:
        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        onTouched(v);
        return true;
      case ACTION_UP:
        if (rect != null &&
            !rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
          // Outside of the view, should be handled as a cancelled event.
          onReleased(v, false);
          return true;
        }
        // Clicked item
        onReleased(v, true);
        return true;
      case ACTION_CANCEL:
        onReleased(v, false);
        return true;
      default:
        return true;
    }
  }

}
