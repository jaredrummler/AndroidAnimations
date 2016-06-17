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

package com.jrummyapps.android.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Collection of animations.</h1>
 *
 * <h4>Effects</h4>
 *
 * <h4>Attention:</h4>
 * <p>FLASH, PULSE, RUBBER_BAND, SHAKE, SWING, WOBBLE, BOUNCE, TADA, STAND_UP, WAVE</p>
 *
 * <h4>Special:</h4>
 * <p>HINGE, ROLL_IN, ROLL_OUT, LANDING, TAKING_OFF, DROP_OUT</p>
 *
 * <h4>Bounce:</h4>
 * <p>BOUNCE_IN, BOUNCE_IN_DOWN, BOUNCE_IN_LEFT, BOUNCE_IN_RIGHT, BOUNCE_IN_UP</p>
 *
 * <h4>Fade:</h4>
 * <p>FADE_IN, FADE_IN_UP, FADE_IN_DOWN, FADE_IN_LEFT, FADE_IN_RIGHT, FADE_OUT, FADE_OUT_DOWN, FADE_OUT_LEFT,
 * FADE_OUT_RIGHT, FADE_OUT_UP</p>
 *
 * <h4>Flip:</h4>
 * <p>FLIP_IN_X, FLIP_OUT_X, FLIP_OUT_Y</p>
 *
 * <h4>Rotate:</h4>
 * <p>ROTATE, ROTATE_IN, ROTATE_IN_DOWN_LEFT, ROTATE_IN_DOWN_RIGHT, ROTATE_IN_UP_LEFT, ROTATE_IN_UP_RIGHT, ROTATE_OUT,
 * ROTATE_OUT_DOWN_LEFT, ROTATE_OUT_DOWN_RIGHT, ROTATE_OUT_UP_LEFT, ROTATE_OUT_UP_RIGHT</p>
 *
 * <h4>Slide:</h4>
 * <p>SLIDE_IN_LEFT, SLIDE_IN_RIGHT, SLIDE_IN_UP, SLIDE_IN_DOWN, SLIDE_OUT_LEFT, SLIDE_OUT_RIGHT, SLIDE_OUT_UP,
 * SLIDE_OUT_DOWN</p>
 *
 * <h4>Zoom:</h4>
 * <p>ZOOM_IN, ZOOM_IN_DOWN, ZOOM_IN_LEFT, ZOOM_IN_RIGHT, ZOOM_IN_UP, ZOOM_OUT, ZOOM_OUT_DOWN, ZOOM_OUT_LEFT,
 * ZOOM_OUT_RIGHT, ZOOM_OUT_UP</p>
 *
 * <p>Example usage:</p>
 *
 * <pre>
 *   Technique.BOUNCE.playOn(view);
 *
 *   Technique.FADE_IN.getComposer()
 *       .duration(2500)
 *       .delay(1000)
 *       .playOn(anotherView);
 * </pre>
 *
 * Based off <a href="https://github.com/daimajia/AndroidViewAnimations">AndroidViewAnimations</a>
 */
public enum Technique {

  /* ------------------------------------------------------------------------------------------- */
  /* Attention
  /* ------------------------------------------------------------------------------------------- */

  FLASH {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0, 1, 0, 1));
        }
      };
    }
  },
  PULSE {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "scaleY", 1, 1.1f, 1),
              ObjectAnimator.ofFloat(target, "scaleX", 1, 1.1f, 1));
        }
      };
    }
  },
  RUBBER_BAND {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "scaleX", 1, 1.25f, 0.75f, 1.15f, 1),
              ObjectAnimator.ofFloat(target, "scaleY", 1, 0.75f, 1.25f, 0.85f, 1));
        }
      };
    }
  },
  SHAKE {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "translationX",
                  0, 25, -25, 25, -25, 15, -15, 6, -6, 0));
        }
      };
    }
  },
  SWING {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "rotation", 0, 10, -10, 6, -6, 3, -3, 0));
        }
      };
    }
  },
  WOBBLE {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float width = target.getWidth();
          float one = (float) (width / 100.0);
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "translationX",
                  0 * one, -25 * one, 20 * one, -15 * one, 10 * one, -5 * one, 0 * one, 0),
              ObjectAnimator.ofFloat(target, "rotation",
                  0, -5, 3, -3, 2, -1, 0));
        }
      };
    }
  },
  BOUNCE {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "translationY", 0, 0, -30, 0, -15, 0, 0));
        }
      };
    }
  },
  TADA {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "scaleX",
                  1, 0.9f, 0.9f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1),
              ObjectAnimator.ofFloat(target, "scaleY",
                  1, 0.9f, 0.9f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1),
              ObjectAnimator.ofFloat(target, "rotation",
                  0, -3, -3, 3, -3, 3, -3, 3, -3, 0));
        }
      };
    }
  },
  STAND_UP {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float x = (target.getWidth() - target.getPaddingLeft() - target.getPaddingRight()) / 2
              + target.getPaddingLeft();
          float y = target.getHeight() - target.getPaddingBottom();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "pivotX", x, x, x, x, x),
              ObjectAnimator.ofFloat(target, "pivotY", y, y, y, y, y),
              ObjectAnimator.ofFloat(target, "rotationX", 55, -30, 15, -15, 0));
        }
      };
    }
  },
  WAVE {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float x = (target.getWidth() - target.getPaddingLeft() - target.getPaddingRight()) / 2
              + target.getPaddingLeft();
          float y = target.getHeight() - target.getPaddingBottom();
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "rotation", 12, -12, 3, -3, 0),
              ObjectAnimator.ofFloat(target, "pivotX", x, x, x, x, x),
              ObjectAnimator.ofFloat(target, "pivotY", y, y, y, y, y));
        }
      };
    }
  },

  /* ------------------------------------------------------------------------------------------- */
  /* Special
  /* ------------------------------------------------------------------------------------------- */

  HINGE {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float x = target.getPaddingLeft();
          float y = target.getPaddingTop();
          getAnimatorSet().playTogether(
              Skill.SINE_EASE_IN_OUT.glide(1300,
                  ObjectAnimator.ofFloat(target, "rotation", 0, 80, 60, 80, 60, 60)),
              ObjectAnimator.ofFloat(target, "translationY", 0, 0, 0, 0, 0, 700),
              ObjectAnimator.ofFloat(target, "alpha", 1, 1, 1, 1, 1, 0),
              ObjectAnimator.ofFloat(target, "pivotX", x, x, x, x, x, x),
              ObjectAnimator.ofFloat(target, "pivotY", y, y, y, y, y, y));

          setDuration(1300);
        }
      };
    }
  },
  ROLL_IN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "translationX",
                  -(target.getWidth() - target.getPaddingLeft() - target.getPaddingRight()), 0),
              ObjectAnimator.ofFloat(target, "rotation", -120, 0));
        }
      };
    }
  },
  ROLL_OUT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "translationX", 0, target.getWidth()),
              ObjectAnimator.ofFloat(target, "rotation", 0, 120));
        }
      };
    }
  },
  LANDING {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          long duration = getAnimatorSet().getDuration();
          getAnimatorSet().playTogether(
              Skill.QUINT_EASE_OUT.glide(duration,
                  ObjectAnimator.ofFloat(target, "scaleX", 1.5f, 1f)),
              Skill.QUINT_EASE_OUT.glide(duration,
                  ObjectAnimator.ofFloat(target, "scaleY", 1.5f, 1f)),
              Skill.QUINT_EASE_OUT.glide(duration,
                  ObjectAnimator.ofFloat(target, "alpha", 0, 1f)));
        }
      };
    }
  },
  TAKING_OFF {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          long duration = getAnimatorSet().getDuration();
          getAnimatorSet().playTogether(
              Skill.QUINT_EASE_OUT
                  .glide(duration, ObjectAnimator.ofFloat(target, "scaleX", 1f, 1.5f)),
              Skill.QUINT_EASE_OUT
                  .glide(duration, ObjectAnimator.ofFloat(target, "scaleY", 1f, 1.5f)),
              Skill.QUINT_EASE_OUT
                  .glide(duration, ObjectAnimator.ofFloat(target, "alpha", 1, 0)));
        }
      };
    }
  },
  DROP_OUT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          int distance = target.getTop() + target.getHeight();
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              Skill.BOUNCE_EASE_OUT.glide(getAnimatorSet().getDuration(),
                  ObjectAnimator.ofFloat(target, "translationY", -distance, 0))
          );
        }
      };
    }
  },

  /* ------------------------------------------------------------------------------------------- */
  /* Bounce
  /* ------------------------------------------------------------------------------------------- */

  BOUNCE_IN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1, 1, 1),
              ObjectAnimator.ofFloat(target, "scaleX", 0.3f, 1.05f, 0.9f, 1),
              ObjectAnimator.ofFloat(target, "scaleY", 0.3f, 1.05f, 0.9f, 1));
        }
      };
    }
  },
  BOUNCE_IN_DOWN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1, 1, 1),
              ObjectAnimator.ofFloat(target, "translationY", -target.getHeight(), 30, -10, 0));
        }
      };
    }
  },
  BOUNCE_IN_LEFT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "translationX", -target.getWidth(), 30, -10, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1, 1, 1));
        }
      };
    }
  },
  BOUNCE_IN_RIGHT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "translationX",
                  target.getMeasuredWidth() + target.getWidth(), -30, 10, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1, 1, 1));
        }
      };
    }
  },
  BOUNCE_IN_UP {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator
                  .ofFloat(target, "translationY", target.getMeasuredHeight(), -30, 10, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1, 1, 1));
        }
      };
    }
  },

  /* ------------------------------------------------------------------------------------------- */
  /* Fade
  /* ------------------------------------------------------------------------------------------- */

  FADE_IN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1));
        }
      };
    }
  },
  FADE_IN_UP {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "translationY", target.getHeight() / 4, 0));
        }
      };
    }
  },
  FADE_IN_DOWN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "translationY", -target.getHeight() / 4, 0));
        }
      };
    }
  },
  FADE_IN_LEFT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "translationX", -target.getWidth() / 4, 0));
        }
      };
    }
  },
  FADE_IN_RIGHT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "translationX", target.getWidth() / 4, 0));
        }
      };
    }
  },
  FADE_OUT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0));
        }
      };
    }
  },
  FADE_OUT_DOWN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "translationY", 0, target.getHeight() / 4));
        }
      };
    }
  },
  FADE_OUT_LEFT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "translationX", 0, -target.getWidth() / 4));
        }
      };
    }
  },
  FADE_OUT_RIGHT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "translationX", 0, target.getWidth() / 4));
        }
      };
    }
  },
  FADE_OUT_UP {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "translationY", 0, -target.getHeight() / 4));
        }
      };
    }
  },

  /* ------------------------------------------------------------------------------------------- */
  /* Flip
  /* ------------------------------------------------------------------------------------------- */

  FLIP_IN_X {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "rotationX", 90, -15, 15, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0.25f, 0.5f, 0.75f, 1));
        }
      };
    }
  },
  FLIP_OUT_X {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "rotationX", 0, 90),
              ObjectAnimator.ofFloat(target, "alpha", 1, 0));
        }
      };
    }
  },
  FLIP_IN_Y {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "rotationY", 90, -15, 15, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0.25f, 0.5f, 0.75f, 1));
        }
      };
    }
  },
  FLIP_OUT_Y {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "rotationY", 0, 90),
              ObjectAnimator.ofFloat(target, "alpha", 1, 0));
        }
      };
    }
  },

  /* ------------------------------------------------------------------------------------------- */
  /* Rotate
  /* ------------------------------------------------------------------------------------------- */

  ROTATE {
    @Override
    public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "rotation", 360, 0));
        }
      };
    }
  },
  ROTATE_IN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "rotation", -200, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1));
        }
      };
    }
  },
  ROTATE_IN_DOWN_LEFT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float x = target.getPaddingLeft();
          float y = target.getHeight() - target.getPaddingBottom();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "rotation", -90, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "pivotX", x, x),
              ObjectAnimator.ofFloat(target, "pivotY", y, y));
        }
      };
    }
  },
  ROTATE_IN_DOWN_RIGHT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float x = target.getWidth() - target.getPaddingRight();
          float y = target.getHeight() - target.getPaddingBottom();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "rotation", 90, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "pivotX", x, x),
              ObjectAnimator.ofFloat(target, "pivotY", y, y));
        }
      };
    }
  },
  ROTATE_IN_UP_LEFT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float x = target.getPaddingLeft();
          float y = target.getHeight() - target.getPaddingBottom();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "rotation", 90, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "pivotX", x, x),
              ObjectAnimator.ofFloat(target, "pivotY", y, y));
        }
      };
    }
  },
  ROTATE_IN_UP_RIGHT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float x = target.getWidth() - target.getPaddingRight();
          float y = target.getHeight() - target.getPaddingBottom();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "rotation", -90, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "pivotX", x, x),
              ObjectAnimator.ofFloat(target, "pivotY", y, y));
        }
      };
    }
  },
  ROTATE_OUT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "rotation", 0, 200));
        }
      };
    }
  },
  ROTATE_OUT_DOWN_LEFT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float x = target.getPaddingLeft();
          float y = target.getHeight() - target.getPaddingBottom();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "rotation", 0, 90),
              ObjectAnimator.ofFloat(target, "pivotX", x, x),
              ObjectAnimator.ofFloat(target, "pivotY", y, y));
        }
      };
    }
  },
  ROTATE_OUT_DOWN_RIGHT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float x = target.getWidth() - target.getPaddingRight();
          float y = target.getHeight() - target.getPaddingBottom();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "rotation", 0, -90),
              ObjectAnimator.ofFloat(target, "pivotX", x, x),
              ObjectAnimator.ofFloat(target, "pivotY", y, y));
        }
      };
    }
  },
  ROTATE_OUT_UP_LEFT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float x = target.getPaddingLeft();
          float y = target.getHeight() - target.getPaddingBottom();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "rotation", 0, -90),
              ObjectAnimator.ofFloat(target, "pivotX", x, x),
              ObjectAnimator.ofFloat(target, "pivotY", y, y));
        }
      };
    }
  },
  ROTATE_OUT_UP_RIGHT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          float x = target.getWidth() - target.getPaddingRight();
          float y = target.getHeight() - target.getPaddingBottom();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "rotation", 0, 90),
              ObjectAnimator.ofFloat(target, "pivotX", x, x),
              ObjectAnimator.ofFloat(target, "pivotY", y, y));
        }
      };
    }
  },

  /* ------------------------------------------------------------------------------------------- */
  /* Slide
  /* ------------------------------------------------------------------------------------------- */

  SLIDE_IN_LEFT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          ViewGroup parent = (ViewGroup) target.getParent();
          int distance = parent.getWidth() - target.getLeft();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "translationX", -distance, 0));
        }
      };
    }
  },
  SLIDE_IN_RIGHT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          ViewGroup parent = (ViewGroup) target.getParent();
          int distance = parent.getWidth() - target.getLeft();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "translationX", distance, 0));
        }
      };
    }
  },
  SLIDE_IN_UP {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          ViewGroup parent = (ViewGroup) target.getParent();
          int distance = parent.getHeight() - target.getTop();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "translationY", distance, 0));
        }
      };
    }
  },
  SLIDE_IN_DOWN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          int distance = target.getTop() + target.getHeight();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1),
              ObjectAnimator.ofFloat(target, "translationY", -distance, 0));
        }
      };
    }
  },
  SLIDE_OUT_LEFT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "translationX", 0, -target.getRight()));
        }
      };
    }
  },
  SLIDE_OUT_RIGHT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          ViewGroup parent = (ViewGroup) target.getParent();
          int distance = parent.getWidth() - target.getLeft();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "translationX", 0, distance));
        }
      };
    }
  },
  SLIDE_OUT_UP {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "translationY", 0, -target.getBottom()));
        }
      };
    }
  },
  SLIDE_OUT_DOWN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          ViewGroup parent = (ViewGroup) target.getParent();
          int distance = parent.getHeight() - target.getTop();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0),
              ObjectAnimator.ofFloat(target, "translationY", 0, distance));
        }
      };
    }
  },

  /* ------------------------------------------------------------------------------------------- */
  /* Zoom
  /* ------------------------------------------------------------------------------------------- */

  ZOOM_IN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "scaleX", 0.45f, 1),
              ObjectAnimator.ofFloat(target, "scaleY", 0.45f, 1),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1));
        }
      };
    }
  },
  ZOOM_IN_DOWN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "scaleX", 0.1f, 0.475f, 1),
              ObjectAnimator.ofFloat(target, "scaleY", 0.1f, 0.475f, 1),
              ObjectAnimator.ofFloat(target, "translationY", -target.getBottom(), 60, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1, 1));
        }
      };
    }
  },
  ZOOM_IN_LEFT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "scaleX", 0.1f, 0.475f, 1),
              ObjectAnimator.ofFloat(target, "scaleY", 0.1f, 0.475f, 1),
              ObjectAnimator.ofFloat(target, "translationX",
                  target.getWidth() + target.getPaddingRight(), -48, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1, 1));
        }
      };
    }
  },
  ZOOM_IN_RIGHT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(
              ObjectAnimator.ofFloat(target, "scaleX", 0.1f, 0.475f, 1),
              ObjectAnimator.ofFloat(target, "scaleY", 0.1f, 0.475f, 1),
              ObjectAnimator.ofFloat(target, "translationX",
                  target.getWidth() + target.getPaddingRight(), -48, 0),
              ObjectAnimator.ofFloat(target, "alpha", 0, 1, 1));
        }
      };
    }
  },
  ZOOM_IN_UP {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          ViewGroup parent = (ViewGroup) target.getParent();
          int distance = parent.getHeight() - target.getTop();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 0, 1, 1),
              ObjectAnimator.ofFloat(target, "scaleX", 0.1f, 0.475f, 1),
              ObjectAnimator.ofFloat(target, "scaleY", 0.1f, 0.475f, 1),
              ObjectAnimator.ofFloat(target, "translationY", distance, -60, 0));
        }
      };
    }
  },
  ZOOM_OUT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 0, 0),
              ObjectAnimator.ofFloat(target, "scaleX", 1, 0.3f, 0),
              ObjectAnimator.ofFloat(target, "scaleY", 1, 0.3f, 0));
        }
      };
    }
  },
  ZOOM_OUT_DOWN {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          ViewGroup parent = (ViewGroup) target.getParent();
          int distance = parent.getHeight() - target.getTop();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 1, 0),
              ObjectAnimator.ofFloat(target, "scaleX", 1, 0.475f, 0.1f),
              ObjectAnimator.ofFloat(target, "scaleY", 1, 0.475f, 0.1f),
              ObjectAnimator.ofFloat(target, "translationY", 0, -60, distance));
        }
      };
    }
  },
  ZOOM_OUT_LEFT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 1, 0),
              ObjectAnimator.ofFloat(target, "scaleX", 1, 0.475f, 0.1f),
              ObjectAnimator.ofFloat(target, "scaleY", 1, 0.475f, 0.1f),
              ObjectAnimator.ofFloat(target, "translationX", 0, 42, -target.getRight()));
        }
      };
    }
  },
  ZOOM_OUT_RIGHT {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          ViewGroup parent = (ViewGroup) target.getParent();
          int distance = parent.getWidth() - parent.getLeft();
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 1, 0),
              ObjectAnimator.ofFloat(target, "scaleX", 1, 0.475f, 0.1f),
              ObjectAnimator.ofFloat(target, "scaleY", 1, 0.475f, 0.1f),
              ObjectAnimator.ofFloat(target, "translationX", 0, -42, distance));
        }
      };
    }
  },
  ZOOM_OUT_UP {
    @Override public SimpleAnimator getAnimator() {
      return new SimpleAnimator() {

        @Override protected void prepare(View target) {
          getAnimatorSet().playTogether(ObjectAnimator.ofFloat(target, "alpha", 1, 1, 0),
              ObjectAnimator.ofFloat(target, "scaleX", 1, 0.475f, 0.1f),
              ObjectAnimator.ofFloat(target, "scaleY", 1, 0.475f, 0.1f),
              ObjectAnimator.ofFloat(target, "translationY", 0, 60, -target.getBottom()));
        }
      };
    }
  };

  /**
   * Get the animator
   *
   * @return A new {@link SimpleAnimator}
   */
  public abstract SimpleAnimator getAnimator();

  /**
   * Compose a new animation.
   *
   * @return A {@link Composer} object used to set the duration, delay, and other parameters for the animation.
   */
  public Composer getComposer() {
    return new Composer(getAnimator());
  }

  /**
   * Plays the animation on the target view. The default duration is 1 second.
   *
   * @param target
   *     the view to play the animation on.
   * @return the animation's {@link Controller} which contains methods to stop or check if the animation is running.
   */
  public Controller playOn(View target) {
    return getComposer().playOn(target);
  }

  /**
   * An abstract class used in each {@link Technique} that plays a set of animations on a view. The class also allows you to
   * set listeners on the animation, delays, duration, etc.
   */
  public static abstract class SimpleAnimator {

    private final AnimatorSet animatorSet = new AnimatorSet();
    private long duration = 1000;
    private View target;

    /**
     * Sets up the {@link AnimatorSet} to play the animations on the view.
     *
     * @param target
     *     the view
     */
    protected abstract void prepare(View target);

    /**
     * Set the start delay
     *
     * @param startDelay
     *     time in milliseconds to wait until playing the animation.
     * @return this {@link SimpleAnimator} object for chaining method calls
     */
    public SimpleAnimator setStartDelay(long startDelay) {
      animatorSet.setStartDelay(startDelay);
      return this;
    }

    /**
     * Adds a listener to the set of listeners that are sent events through the life of an animation, such as start, repeat, and
     * end.
     *
     * @param listener
     *     the listener to be added to the current set of listeners for this animation.
     * @return this {@link SimpleAnimator} object for chaining method calls
     */
    public SimpleAnimator addAnimatorListener(Animator.AnimatorListener listener) {
      animatorSet.addListener(listener);
      return this;
    }

    /**
     * Sets the {@link android.animation.TimeInterpolator} for all current child animations of this {@link AnimatorSet}.
     *
     * @param interpolator
     *     the interpolator to be used by each child animation of this AnimatorSet
     * @return this {@link SimpleAnimator} object for chaining method calls
     */
    public SimpleAnimator setInterpolator(Interpolator interpolator) {
      animatorSet.setInterpolator(interpolator);
      return this;
    }

    /**
     * Set the duration of the animation.
     *
     * @param duration
     *     the duration in milliseconds
     * @return this {@link SimpleAnimator} object for chaining method calls
     */
    public SimpleAnimator setDuration(long duration) {
      this.duration = duration;
      return this;
    }

    /**
     * Adds a collection of listeners to the set of listeners that are sent events through the life of an animation, such as
     * start, repeat, and end.
     *
     * @param callbacks
     *     a collection of listeners to be added to the current set of listeners for this animation.
     * @return this {@link SimpleAnimator} object for chaining method calls
     */
    public SimpleAnimator setCallbacks(List<Animator.AnimatorListener> callbacks) {
      if (callbacks != null && !callbacks.isEmpty()) {
        for (Animator.AnimatorListener callback : callbacks) {
          animatorSet.addListener(callback);
        }
      }
      return this;
    }

    /**
     * Set the view to play the animation on.
     *
     * @param target
     *     a {@link View}
     * @return this {@link SimpleAnimator} object for chaining method calls
     */
    public SimpleAnimator setTarget(View target) {
      this.target = target;
      return this;
    }

    /**
     * Reset the view to default values.
     */
    public void reset() {
      target.setAlpha(1);
      target.setScaleX(1);
      target.setScaleY(1);
      target.setTranslationX(0);
      target.setTranslationY(0);
      target.setRotation(0);
      target.setRotationY(0);
      target.setRotationX(0);
      target.setPivotX(target.getMeasuredWidth() / 2.0f);
      target.setPivotY(target.getMeasuredHeight() / 2.0f);
    }

    /**
     * Start playing the animation
     *
     * @return the animation's {@link Controller} which contains methods to stop or check if the animation is running.
     */
    public Controller start() {
      reset();
      prepare(target);
      animatorSet.setDuration(duration);
      animatorSet.start();
      return new Controller(this);
    }

    /**
     * @return this animations' {@link AnimatorSet}.
     */
    public AnimatorSet getAnimatorSet() {
      return animatorSet;
    }

    /**
     * @return the {@link View} to play the animation on.
     */
    public View getTarget() {
      return target;
    }

  }

  /**
   * A class to compose/build a {@link SimpleAnimator}.
   */
  public static final class Composer {

    private final List<Animator.AnimatorListener> callbacks = new ArrayList<>();
    private final SimpleAnimator animator;
    private Interpolator interpolator;
    private long duration = 1000;
    private long delay;

    protected Composer(SimpleAnimator animator) {
      this.animator = animator;
    }

    /**
     * Set the duration of the animation.
     *
     * @param duration
     *     the duration in milliseconds
     * @return this {@link Composer} object for chaining method calls.
     */
    public Composer duration(long duration) {
      this.duration = duration;
      return this;
    }

    /**
     * Set the start delay
     *
     * @param delay
     *     time in milliseconds to wait until playing the animation.
     * @return this {@link Composer} object for chaining method calls.
     */
    public Composer delay(long delay) {
      this.delay = delay;
      return this;
    }

    /**
     * Sets the {@link android.animation.TimeInterpolator} for all current child animations of this {@link AnimatorSet}.
     *
     * @param interpolator
     *     the interpolator to be used by each child animation of this AnimatorSet
     * @return this {@link Composer} object for chaining method calls.
     */
    public Composer interpolate(Interpolator interpolator) {
      this.interpolator = interpolator;
      return this;
    }

    /**
     * Adds a listener to the set of listeners that are sent events through the life of an animation, such as start, repeat, and
     * end.
     *
     * @param listener
     *     the listener to be added to the current set of listeners for this animation.
     * @return this {@link Composer} object for chaining method calls.
     */
    public Composer withListener(Animator.AnimatorListener listener) {
      callbacks.add(listener);
      return this;
    }

    /**
     * Sets the target view's visibility to {@link View#GONE} when the animation is finished.
     *
     * @return this {@link Composer} object for chaining method calls.
     */
    public Composer hideOnFinished() {
      return onEnd(new AnimatorCallback() {

        @Override public void call(SimpleAnimator animator) {
          animator.getTarget().setVisibility(View.GONE);
        }
      });
    }

    /**
     * Sets the target view's visibility to {@link View#VISIBLE} before playing the animation.
     *
     * @return this {@link Composer} object for chaining method calls.
     */
    public Composer showOnStart() {
      return onStart(new AnimatorCallback() {

        @Override public void call(SimpleAnimator animator) {
          animator.getTarget().setVisibility(View.VISIBLE);
        }
      });
    }

    /**
     * Add a callback that is invoked when the animation starts.
     *
     * @param callback
     *     the {@link AnimatorCallback}
     * @return this {@link Composer} object for chaining method calls.
     */
    public Composer onStart(final AnimatorCallback callback) {
      callbacks.add(new EmptyAnimatorListener() {

        @Override public void onAnimationStart(Animator animation) {
          callback.call(animator);
        }
      });
      return this;
    }

    /**
     * Add a callback that is invoked when the animation finishes.
     *
     * @param callback
     *     the {@link AnimatorCallback}
     * @return this {@link Composer} object for chaining method calls.
     */
    public Composer onEnd(final AnimatorCallback callback) {
      callbacks.add(new EmptyAnimatorListener() {

        @Override public void onAnimationEnd(Animator animation) {
          callback.call(animator);
        }
      });
      return this;
    }

    /**
     * Add a callback that is invoked when the animation is cancelled.
     *
     * @param callback
     *     the {@link AnimatorCallback}
     * @return this {@link Composer} object for chaining method calls.
     */
    public Composer onCancel(final AnimatorCallback callback) {
      callbacks.add(new EmptyAnimatorListener() {

        @Override public void onAnimationCancel(Animator animation) {
          callback.call(animator);
        }
      });
      return this;
    }

    /**
     * Add a callback that is invoked when the animation is repeated.
     *
     * @param callback
     *     the {@link AnimatorCallback}
     * @return this {@link Composer} object for chaining method calls.
     */
    public Composer onRepeat(final AnimatorCallback callback) {
      callbacks.add(new EmptyAnimatorListener() {

        @Override public void onAnimationRepeat(Animator animation) {
          callback.call(animator);
        }
      });
      return this;
    }

    /**
     * Play the animation
     *
     * @param target
     *     the view to play the animation on.
     * @return the animation's {@link Controller} which contains methods to stop or check if the animation is running.
     */
    public Controller playOn(View target) {
      return animator.setTarget(target)
          .setDuration(duration)
          .setInterpolator(interpolator)
          .setStartDelay(delay)
          .setCallbacks(callbacks)
          .start();
    }

  }

  /**
   * Allows you to stop the animation and check if the animation has started and is running.
   */
  public static final class Controller {

    private final SimpleAnimator animator;

    private Controller(SimpleAnimator animator) {
      this.animator = animator;
    }

    /**
     * Returns whether this Animator has been started and not yet ended.
     *
     * @return Whether the Animator has been started and not yet ended.
     */
    @TargetApi(VERSION_CODES.ICE_CREAM_SANDWICH)
    public boolean isStarted() {
      return animator.getAnimatorSet().isStarted();
    }

    /**
     * Returns {@code true} if any of the child animations of this AnimatorSet have been started and have not yet ended.
     *
     * @return Whether this AnimatorSet has been started and has not yet ended.
     */
    public boolean isRunning() {
      return animator.getAnimatorSet().isRunning();
    }

    /**
     * <p>Cancels the animation. Unlike end(), cancel() causes the animation to stop in its tracks, sending an
     * onAnimationCancel(Animator) to its listeners, followed by an onAnimationEnd(Animator) message.</p>
     *
     * <p>This method must be called on the thread that is running the animation.</p>
     *
     * <p>Note that canceling a AnimatorSet also cancels all of the animations that it is responsible for.</p>
     *
     * @param reset
     *     {@code true} to reset the view to default values after cancelling.
     */
    public void stop(boolean reset) {
      animator.getAnimatorSet().cancel();
      if (reset) {
        animator.reset();
      }
    }

  }

  /**
   * A callback that is invoked from a {@link Animator.AnimatorListener}.
   */
  public interface AnimatorCallback {

    /**
     * @param animator
     *     the animation
     */
    void call(SimpleAnimator animator);
  }

}
