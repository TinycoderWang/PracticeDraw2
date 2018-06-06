package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice05ComposeShaderView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice05ComposeShaderView(Context context) {
        super(context);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null); // 硬件加速下 ComposeShader 不能使用两个同类型的 Shader

        // 用 Paint.setShader(shader) 设置一个 ComposeShader
        // Shader 1: BitmapShader 图片：R.drawable.batman
        BitmapShader bitmapShader1 = new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.batman), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // Shader 2: BitmapShader 图片：R.drawable.batman_logo
        BitmapShader bitmapShader2 = new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

//                /** [0, 0] */
//                CLEAR       (0),
//                /** [Sa, Sc] */
//                SRC         (1),
//                /** [Da, Dc] */
//                DST         (2),
//                /** [Sa + (1 - Sa)*Da, Rc = Sc + (1 - Sa)*Dc] */
//                SRC_OVER    (3),
//                /** [Sa + (1 - Sa)*Da, Rc = Dc + (1 - Da)*Sc] */
//                DST_OVER    (4),
//                /** [Sa * Da, Sc * Da] */
//                SRC_IN      (5),
//                /** [Sa * Da, Sa * Dc] */
//                DST_IN      (6),
//                /** [Sa * (1 - Da), Sc * (1 - Da)] */
//                SRC_OUT     (7),
//                /** [Da * (1 - Sa), Dc * (1 - Sa)] */
//                DST_OUT     (8),
//                /** [Da, Sc * Da + (1 - Sa) * Dc] */
//                SRC_ATOP    (9),
//                /** [Sa, Sa * Dc + Sc * (1 - Da)] */
//                DST_ATOP    (10),
//                /** [Sa + Da - 2 * Sa * Da, Sc * (1 - Da) + (1 - Sa) * Dc] */
//                XOR         (11),
//                /** [Sa + Da - Sa*Da, Sc*(1 - Da) + Dc*(1 - Sa) + min(Sc, Dc)] */
//                DARKEN      (12),
//                /** [Sa + Da - Sa*Da, Sc*(1 - Da) + Dc*(1 - Sa) + max(Sc, Dc)] */
//                LIGHTEN     (13),
//                /** [Sa * Da, Sc * Dc] */
//                MULTIPLY    (14),
//                /** [Sa + Da - Sa * Da, Sc + Dc - Sc * Dc] */
//                SCREEN      (15),
//                /** Saturate(S + D) */
//                ADD         (16),
//                OVERLAY     (17);

        ComposeShader composeShader = new ComposeShader(bitmapShader1,bitmapShader2, PorterDuff.Mode.DST_IN);
        paint.setShader(composeShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(200, 200, 200, paint);
    }
}
