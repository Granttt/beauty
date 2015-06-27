package beauty.beautydemo.custview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.TypedValue;
import android.view.View;

import beauty.beautydemo.R;

/**
 * @title:			DividerItemDecoration.java
 * @author:			Lou Jiwei
 * @Email:			kkooff114@gmail.com
 * @version 		创建时间：2015-3-26 下午5:16:35
 * @description:
 */
public class DividerItemDecoration extends ItemDecoration{

	private int mOrientation = LinearLayoutManager.VERTICAL ;
	private int mItemSize = 1 ;
	private Paint mPaint ;
    final private int COLOR = R.color.item_bg_purple;//分割线颜色

	/**
      * 构造方法传入布局方向，不可不传
      * @param context
      * @param orientation
      */
     public DividerItemDecoration(Context context,int orientation) {
         this.mOrientation = orientation;
         if(orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL){
             throw new IllegalArgumentException("请传入正确的参数") ;
         }
         mItemSize = (int) TypedValue.applyDimension(mItemSize, TypedValue.COMPLEX_UNIT_DIP,context.getResources().getDisplayMetrics());
         mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) ;
         mPaint.setColor(context.getResources().getColor(COLOR));
         /*设置填充*/
         mPaint.setStyle(Paint.Style.FILL);
     }

	 @Override
     public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
         if(mOrientation == LinearLayoutManager.VERTICAL){
             drawVertical(c,parent) ;
         }else {
             drawHorizontal(c,parent) ;
         }
     }

	 /**
      * 绘制纵向 item 分割线
      * @param canvas
      * @param parent
      */
     private void drawVertical(Canvas canvas,RecyclerView parent){
         final int left = parent.getPaddingLeft() ;
         final int right = parent.getMeasuredWidth() - parent.getPaddingRight() ;
         final int childSize = parent.getChildCount() ;
         for(int i = 0 ; i < childSize ; i ++){
             final View child = parent.getChildAt( i ) ;
             RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
             final int top = child.getBottom() + layoutParams.bottomMargin ;
             final int bottom = top + mItemSize ;
             canvas.drawRect(left,top,right,bottom,mPaint);
         }
     }

     /**
      * 绘制横向 item 分割线
      * @param canvas
      * @param parent
      */
     private void drawHorizontal(Canvas canvas,RecyclerView parent){
         final int top = parent.getPaddingTop() ;
         final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom() ;
         final int childSize = parent.getChildCount() ;
         for(int i = 0 ; i < childSize ; i ++){
             final View child = parent.getChildAt( i ) ;
             RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
             final int left = child.getRight() + layoutParams.rightMargin ;
             final int right = left + mItemSize ;
             canvas.drawRect(left,top,right,bottom,mPaint);
         }
     }

     /**
      * 设置item分割线的size
      * @param outRect
      * @param view
      * @param parent
      * @param state
      */
     @Override
     public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
         if(mOrientation == LinearLayoutManager.VERTICAL){
             outRect.set(0,0,0,mItemSize);
         }else {
             outRect.set(0,0,mItemSize,0);
         }
     }

}