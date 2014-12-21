package net.lightcraftmc.fusebox.util;

import java.util.Random;

public final class MathUtils
{
  public static final float nanoToSec = 1.0E-009F;
  public static final float FLOAT_ROUNDING_ERROR = 1.0E-006F;
  public static final float PI = 3.141593F;
  public static final float PI2 = 6.283186F;
  public static final float SQRT_3 = 1.73205F;
  public static final float E = 2.718282F;
  private static final int SIN_BITS = 14;
  private static final int SIN_MASK = 16383;
  private static final int SIN_COUNT = 16384;
  private static final float radFull = 6.283186F;
  private static final float degFull = 360.0F;
  private static final float radToIndex = 2607.5945F;
  private static final float degToIndex = 45.511112F;
  public static final float radiansToDegrees = 57.295776F;
  public static final float radDeg = 57.295776F;
  public static final float degreesToRadians = 0.01745329F;
  public static final float degRad = 0.01745329F;
  private static final int ATAN2_BITS = 7;
  private static final int ATAN2_BITS2 = 14;
  private static final int ATAN2_MASK = 16383;
  private static final int ATAN2_COUNT = 16384;
  static final int ATAN2_DIM = (int)Math.sqrt(16384.0D);
  private static final float INV_ATAN2_DIM_MINUS_1 = 1.0F / (ATAN2_DIM - 1);

  public static Random random = new Random();
  private static final int BIG_ENOUGH_INT = 16384;
  private static final double BIG_ENOUGH_FLOOR = 16384.0D;
  private static final double CEIL = 0.9999999000000001D;
  private static final double BIG_ENOUGH_CEIL = 16384.999999999996D;
  private static final double BIG_ENOUGH_ROUND = 16384.5D;

  public static final float sin(float radians)
  {
    return Sin.table[((int)(radians * 2607.5945F) & 0x3FFF)];
  }

  public static final float cos(float radians)
  {
    return Sin.table[((int)((radians + 1.570796F) * 2607.5945F) & 0x3FFF)];
  }

  public static final float sinDeg(float degrees)
  {
    return Sin.table[((int)(degrees * 45.511112F) & 0x3FFF)];
  }

  public static final float cosDeg(float degrees)
  {
    return Sin.table[((int)((degrees + 90.0F) * 45.511112F) & 0x3FFF)];
  }

  public static final float atan2(float y, float x)
  {
    float add;
    float mul;
    if (x < 0.0F)
    {
      if (y < 0.0F) {
        y = -y;
        mul = 1.0F;
      } else {
        mul = -1.0F;
      }x = -x;
      add = -3.141593F;
    }
    else
    {
      if (y < 0.0F) {
        y = -y;
        mul = -1.0F;
      } else {
        mul = 1.0F;
      }add = 0.0F;
    }
    float invDiv = 1.0F / ((x < y ? y : x) * INV_ATAN2_DIM_MINUS_1);

    if (invDiv == (1.0F / 1.0F)) return ((float)Math.atan2(y, x) + add) * mul;

    int xi = (int)(x * invDiv);
    int yi = (int)(y * invDiv);
    return (Atan2.table[(yi * ATAN2_DIM + xi)] + add) * mul;
  }

  public static final int random(int range)
  {
    return random.nextInt(range + 1);
  }

  public static final int random(int start, int end)
  {
    return start + random.nextInt(end - start + 1);
  }

  public static final boolean randomBoolean()
  {
    return random.nextBoolean();
  }

  public static final boolean randomBoolean(float chance)
  {
    return random() < chance;
  }

  public static final float random()
  {
    return random.nextFloat();
  }

  public static final float random(float range)
  {
    return random.nextFloat() * range;
  }

  public static final float random(float start, float end)
  {
    return start + random.nextFloat() * (end - start);
  }

  public static int nextPowerOfTwo(int value)
  {
    if (value == 0) return 1;
    value--;
    value |= value >> 1;
    value |= value >> 2;
    value |= value >> 4;
    value |= value >> 8;
    value |= value >> 16;
    return value + 1;
  }

  public static boolean isPowerOfTwo(int value) {
    return (value != 0) && ((value & value - 1) == 0);
  }

  public static int clamp(int value, int min, int max)
  {
    if (value < min) return min;
    if (value > max) return max;
    return value;
  }

  public static short clamp(short value, short min, short max) {
    if (value < min) return min;
    if (value > max) return max;
    return value;
  }

  public static float clamp(float value, float min, float max) {
    if (value < min) return min;
    if (value > max) return max;
    return value;
  }

  public static int floor(float x)
  {
    return (int)(x + 16384.0D) - 16384;
  }

  public static int floorPositive(float x)
  {
    return (int)x;
  }

  public static int ceil(float x)
  {
    return (int)(x + 16384.999999999996D) - 16384;
  }

  public static int ceilPositive(float x)
  {
    return (int)(x + 0.9999999000000001D);
  }

  public static int round(float x)
  {
    return (int)(x + 16384.5D) - 16384;
  }

  public static int roundPositive(float x)
  {
    return (int)(x + 0.5F);
  }

  public static boolean isZero(float value)
  {
    return Math.abs(value) <= 1.0E-006F;
  }

  public static boolean isZero(float value, float tolerance)
  {
    return Math.abs(value) <= tolerance;
  }

  public static boolean isEqual(float a, float b)
  {
    return Math.abs(a - b) <= 1.0E-006F;
  }

  public static boolean isEqual(float a, float b, float tolerance)
  {
    return Math.abs(a - b) <= tolerance;
  }

  private static class Atan2
  {
    static final float[] table = new float[16384];

    static { for (int i = 0; i < MathUtils.ATAN2_DIM; i++)
        for (int j = 0; j < MathUtils.ATAN2_DIM; j++) {
          float x0 = i / MathUtils.ATAN2_DIM;
          float y0 = j / MathUtils.ATAN2_DIM;
          table[(j * MathUtils.ATAN2_DIM + i)] = ((float)Math.atan2(y0, x0));
        }
    }
  }

  private static class Sin
  {
    static final float[] table = new float[16384];

    static { for (int i = 0; i < 16384; i++)
        table[i] = ((float)Math.sin((i + 0.5F) / 16384.0F * 6.283186F));
      for (int i = 0; i < 360; i += 90)
        table[((int)(i * 45.511112F) & 0x3FFF)] = ((float)Math.sin(i * 0.01745329F));
    }
  }
}