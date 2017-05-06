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

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <p>Android easing functions. An animation collection to help make animation easier.</p>
 *
 * <p>Example usage:</p>
 *
 * <pre>
 * AnimatorSet set = new AnimatorSet();
 * set.playTogether(Skill.BOUNCE_EASE_IN_OUT.glide(
 *     1200, ObjectAnimator.ofFloat(view, "translationY", 0, 100)));
 * set.setDuration(1200);
 * set.start();
 * </pre>
 *
 * Based on <a href="https://github.com/daimajia/AnimationEasingFunctions">AnimationEasingFunctions</a>
 */
public enum Skill {
  BACK_EASE_IN {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return c * (t /= d) * t * ((1.70158f + 1) * t - 1.70158f) + b;
        }
      };
    }
  },
  BACK_EASE_IN_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return getMethod(duration, 1.70158f);
    }

    public EasingMethod getMethod(float duration, final float back) {
      return new EasingMethod(duration) {

        private float s = back;

        @Override public float calculate(float t, float b, float c, float d) {
          return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
        }
      };
    }
  },
  BACK_EASE_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return getMethod(duration, 1.70158f);
    }

    public EasingMethod getMethod(float duration, final float back) {
      return new EasingMethod(duration) {

        private float s = back;

        @Override public float calculate(float t, float b, float c, float d) {
          return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
        }
      };
    }

  },
  BOUNCE_EASE_IN {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        private final EasingMethod bounceEaseOut = BOUNCE_EASE_OUT.getMethod(duration);

        @Override public float calculate(float t, float b, float c, float d) {
          return c - bounceEaseOut.calculate(d - t, 0, c, d) + b;
        }
      };
    }
  },
  BOUNCE_EASE_IN_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        private final EasingMethod bounceEaseOut = BOUNCE_EASE_OUT.getMethod(duration);

        private final EasingMethod bounceEaseIn = BOUNCE_EASE_IN.getMethod(duration);

        @Override public float calculate(float t, float b, float c, float d) {
          if (t < d / 2) {
            return bounceEaseIn.calculate(t * 2, 0, c, d) * .5f + b;
          } else {
            return bounceEaseOut.calculate(t * 2 - d, 0, c, d) * .5f + c * .5f + b;
          }
        }
      };
    }
  },
  BOUNCE_EASE_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          if ((t /= d) < (1 / 2.75f)) {
            return c * (7.5625f * t * t) + b;
          } else if (t < (2 / 2.75f)) {
            return c * (7.5625f * (t -= (1.5f / 2.75f)) * t + .75f) + b;
          } else if (t < (2.5 / 2.75)) {
            return c * (7.5625f * (t -= (2.25f / 2.75f)) * t + .9375f) + b;
          } else {
            return c * (7.5625f * (t -= (2.625f / 2.75f)) * t + .984375f) + b;
          }
        }
      };
    }
  },
  CIRC_EASE_IN {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return -c * ((float) Math.sqrt(1 - (t /= d) * t) - 1) + b;
        }
      };
    }
  },
  CIRC_EASE_IN_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          if ((t /= d / 2) < 1) {
            return -c / 2 * ((float) Math.sqrt(1 - t * t) - 1) + b;
          }
          return c / 2 * ((float) Math.sqrt(1 - (t -= 2) * t) + 1) + b;
        }
      };
    }
  },
  CIRC_EASE_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return c * (float) Math.sqrt(1 - (t = t / d - 1) * t) + b;
        }
      };
    }
  },
  ELASTIC_EASE_IN {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          if (t == 0) {
            return b;
          }
          if ((t /= d) == 1) {
            return b + c;
          }
          float p = d * .3f;
          float a = c;
          float s = p / 4;
          return -(a * (float) Math.pow(2, 10 * (t -= 1)) * (float) Math.sin((t * d - s)
              * (2 * (float) Math.PI) / p)) + b;
        }
      };
    }
  },
  ELASTIC_EASE_IN_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          if (t == 0) {
            return b;
          }
          if ((t /= d / 2) == 2) {
            return b + c;
          }
          float p = d * (.3f * 1.5f);
          float a = c;
          float s = p / 4;
          if (t < 1) {
            return -.5f
                * (a * (float) Math.pow(2, 10 * (t -= 1)) * (float) Math.sin((t * d - s)
                * (2 * (float) Math.PI) / p)) + b;
          }
          return a * (float) Math.pow(2, -10 * (t -= 1))
              * (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p) * .5f + c + b;
        }
      };
    }
  },
  ELASTIC_EASE_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          if (t == 0) {
            return b;
          }
          if ((t /= d) == 1) {
            return b + c;
          }
          float p = d * .3f;
          float a = c;
          float s = p / 4;
          return (a * (float) Math.pow(2, -10 * t)
              * (float) Math.sin((t * d - s) * (2 * (float) Math.PI) / p) + c + b);
        }
      };
    }
  },
  EXPO_EASE_IN {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return (t == 0) ? b : c * (float) Math.pow(2, 10 * (t / d - 1)) + b;
        }
      };
    }
  },
  EXPO_EASE_IN_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          if (t == 0) {
            return b;
          }
          if (t == d) {
            return b + c;
          }
          if ((t /= d / 2) < 1) {
            return c / 2 * (float) Math.pow(2, 10 * (t - 1)) + b;
          }
          return c / 2 * (-(float) Math.pow(2, -10 * --t) + 2) + b;
        }
      };
    }
  },
  EXPO_EASE_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return (t == d) ? b + c : c * (-(float) Math.pow(2, -10 * t / d) + 1) + b;
        }
      };
    }
  },
  LINEAR {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return c * t / d + b;
        }
      };
    }
  },
  QUAD_EASE_IN {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return c * (t /= d) * t + b;
        }
      };
    }
  },
  QUAD_EASE_IN_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          if ((t /= d / 2) < 1) {
            return c / 2 * t * t + b;
          }
          return -c / 2 * ((--t) * (t - 2) - 1) + b;
        }
      };
    }
  },
  QUAD_EASE_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return -c * (t /= d) * (t - 2) + b;
        }
      };
    }
  },
  QUINT_EASE_IN {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return c * (t /= d) * t * t * t * t + b;
        }
      };
    }
  },
  QUINT_EASE_IN_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          if ((t /= d / 2) < 1) {
            return c / 2 * t * t * t * t * t + b;
          }
          return c / 2 * ((t -= 2) * t * t * t * t + 2) + b;
        }
      };
    }
  },
  QUINT_EASE_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return c * ((t = t / d - 1) * t * t * t * t + 1) + b;
        }
      };
    }
  },
  SINE_EASE_IN {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return -c * (float) Math.cos(t / d * (Math.PI / 2)) + c + b;
        }
      };
    }
  },
  SINE_EASE_IN_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return -c / 2 * ((float) Math.cos(Math.PI * t / d) - 1) + b;
        }
      };
    }
  },
  SINE_EASE_OUT {
    @Override public EasingMethod getMethod(float duration) {
      return new EasingMethod(duration) {

        @Override public float calculate(float t, float b, float c, float d) {
          return c * (float) Math.sin(t / d * (Math.PI / 2)) + b;
        }
      };
    }
  };

  /**
   * Get the {@link EasingMethod} for this {@link Skill}.
   *
   * @param duration
   *     the duration of the animation
   * @return the {@link EasingMethod}
   */
  public abstract EasingMethod getMethod(float duration);

  /**
   * Get the {@link ValueAnimator} for this {@link Skill}.
   *
   * @param duration
   *     the duration of the animation
   * @param animator
   *     an animator, usually an {@link ObjectAnimator}, to set the {@link EasingMethod} on.
   * @return the {@link ValueAnimator}
   */
  public ValueAnimator glide(float duration, ValueAnimator animator) {
    return glide(duration, animator, (EasingMethod.EasingListener[]) null);
  }

  /**
   * Get the {@link ValueAnimator} for this {@link Skill}.
   *
   * @param duration
   *     the duration of the animation
   * @param animator
   *     an animator, usually an {@link ObjectAnimator}, to set the {@link EasingMethod} on.
   * @param listeners
   *     the animation listeners
   * @return the {@link ValueAnimator}
   */
  public ValueAnimator glide(float duration, ValueAnimator animator, EasingMethod.EasingListener... listeners) {
    EasingMethod method = getMethod(duration);
    if (listeners != null && listeners.length > 0) {
      method.addEasingListeners(listeners);
    }
    animator.setEvaluator(method);
    return animator;
  }

  /**
   * Get the {@link PropertyValuesHolder} for this {@link Skill}.
   *
   * @param duration
   *     the duration of the animation
   * @param propertyValuesHolder
   *     an animator to set the {@link EasingMethod} on.
   * @return the {@link PropertyValuesHolder}
   */
  public PropertyValuesHolder glide(float duration, PropertyValuesHolder propertyValuesHolder) {
    propertyValuesHolder.setEvaluator(getMethod(duration));
    return propertyValuesHolder;
  }

  public static abstract class EasingMethod implements TypeEvaluator<Number> {

    private final ArrayList<EasingListener> listeners = new ArrayList<>();

    protected float duration;

    public EasingMethod(float duration) {
      this.duration = duration;
    }

    /**
     * Add a listener to the animator
     *
     * @param listener
     *     the {@link EasingListener}
     */
    public void addEasingListener(EasingListener listener) {
      listeners.add(listener);
    }

    /**
     * Add listeners to the animator.
     *
     * @param listeners
     *     the {@link EasingListener}(s)
     */
    public void addEasingListeners(EasingListener... listeners) {
      Collections.addAll(this.listeners, listeners);
    }

    /**
     * Remove a listener.
     *
     * @param listener
     *     The listener to remove.
     */
    public void removeEasingListener(EasingListener listener) {
      listeners.remove(listener);
    }

    /**
     * Clear all listeners.
     */
    public void clearEasingListeners() {
      listeners.clear();
    }

    /**
     * Set the duration of the animation.
     *
     * @param duration
     *     the time in milliseconds.
     */
    public void setDuration(float duration) {
      this.duration = duration;
    }

    @Override public final Float evaluate(float fraction, Number startValue, Number endValue) {
      float t = duration * fraction;
      float b = startValue.floatValue();
      float c = endValue.floatValue() - startValue.floatValue();
      float d = duration;
      float result = calculate(t, b, c, d);
      for (EasingListener l : listeners) {
        l.on(t, result, b, c, d);
      }
      return result;
    }

    public abstract float calculate(float t, float b, float c, float d);

    public interface EasingListener {

      void on(float time, float value, float start, float end, float duration);

    }

  }

}
