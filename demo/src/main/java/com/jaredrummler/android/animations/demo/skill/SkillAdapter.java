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

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jaredrummler.android.animations.Skill;
import com.jaredrummler.android.animations.demo.R;

import java.util.Locale;

public class SkillAdapter extends BaseAdapter {

  private static String capitalize(String str) {
    if (TextUtils.isEmpty(str)) {
      return str;
    }
    char[] arr = str.toCharArray();
    boolean capitalizeNext = true;
    String phrase = "";
    for (char c : arr) {
      if (capitalizeNext && Character.isLetter(c)) {
        phrase += Character.toUpperCase(c);
        capitalizeNext = false;
        continue;
      } else if (Character.isWhitespace(c)) {
        capitalizeNext = true;
      }
      phrase += c;
    }
    return phrase;
  }

  @Override public int getCount() {
    return Skill.values().length;
  }

  @Override public Skill getItem(int position) {
    return Skill.values()[position];
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    final ViewHolder holder;

    if (convertView == null) {
      convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
      holder = new ViewHolder(convertView);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }

    Skill skill = getItem(position);
    holder.textView.setText(capitalize(skill.name().toLowerCase(Locale.ENGLISH).replaceAll("_", " ")));

    return convertView;
  }

  class ViewHolder {

    TextView textView;

    ViewHolder(View v) {
      textView = (TextView) v.findViewById(R.id.list_item_text);
      v.setTag(this);
    }

  }

}
