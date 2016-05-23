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

package com.jrummyapps.android.animations.demo.skill;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jrummyapps.android.animations.Skill;
import com.jrummyapps.android.animations.demo.R;

public class SkillDemoActivity extends AppCompatActivity {

  private static float dp2px(Context context, float dipValue) {
    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_skill_demo);

    final ListView listView = (ListView) findViewById(R.id.easing_list);
    final SkillAdapter skillAdapter = new SkillAdapter();
    final View targetView = findViewById(R.id.target);
    final DrawView drawView = (DrawView) findViewById(R.id.history);
    final float endValue = dp2px(getApplicationContext(), -157);

    listView.setAdapter(skillAdapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        drawView.clear();
        Skill skill = Skill.values()[position];
        AnimatorSet set = new AnimatorSet();
        targetView.setTranslationX(0);
        targetView.setTranslationY(0);
        set.playTogether(skill.glide(1200, ObjectAnimator.ofFloat(targetView, "translationY", 0, endValue),
            new Skill.EasingMethod.EasingListener() {

              @Override public void on(float time, float value, float start, float end, float duration) {
                drawView.drawPoint(time, duration, value - dp2px(getApplicationContext(), 60));
              }
            })
        );
        set.setDuration(1200);
        set.start();
      }
    });

  }

}
