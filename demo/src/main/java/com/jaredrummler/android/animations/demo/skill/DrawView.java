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

package com.jaredrummler.android.animations.demo.skill;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class DrawView extends View {

  private final Paint backgroundPaint = new Paint();
  private final Paint linePaint = new Paint();
  private final Path path = new Path();
  private boolean start = false;

  {
    backgroundPaint.setColor(Color.WHITE);
    linePaint.setColor(Color.rgb(77, 83, 96));
    linePaint.setAntiAlias(true);
    linePaint.setStrokeWidth((float) 3.0);
    linePaint.setStyle(Paint.Style.STROKE);
  }

  public DrawView(Context context) {
    super(context);
  }

  public DrawView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    float l = 0;
    float t = getHeight() - getPaddingBottom() - dipToPixels(getContext(), 217);
    float r = getWidth() - getPaddingRight();
    float b = getHeight() - dipToPixels(getContext(), 60);
    canvas.drawRect(l, t, r, b, linePaint);
    canvas.drawPath(path, linePaint);
  }

  public static float dipToPixels(Context context, float dipValue) {
    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
  }

  public void drawPoint(float time, float duration, float y) {
    float p = time / duration;
    float x = p * getWidth();
    float z = getHeight() + y;
    if (!start) {
      path.moveTo(x, z);
      start = true;
    }
    path.lineTo(x, z);
    invalidate();
  }

  public void clear() {
    path.reset();
    start = false;
    invalidate();
  }

}