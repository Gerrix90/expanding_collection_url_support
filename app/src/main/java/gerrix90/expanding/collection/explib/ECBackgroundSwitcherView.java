package gerrix90.expanding.collection.explib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import gerrix90.expanding.collection.comments.ExampleDataset;

/**
 * Custom Image Switcher for display and change background images with some pretty animations.
 * Uses different drawing orders for animation purposes.
 */
public class ECBackgroundSwitcherView extends ImageSwitcher {
    private final int[] REVERSE_ORDER = new int[]{1, 0};
    private final int[] NORMAL_ORDER = new int[]{0, 1};

    private boolean reverseDrawOrder;

    private int bgImageGap;
    private int bgImageWidth;

    private int alphaDuration = 400;
    private int movementDuration = 500;
    private int widthBackgroundImageGapPercent = 12;

    private Animation bgImageInLeftAnimation;
    private Animation bgImageOutLeftAnimation;

    private Animation bgImageInRightAnimation;
    private Animation bgImageOutRightAnimation;

    private AnimationDirection currentAnimationDirection;

    private BitmapWorkerTask mCurrentAnimationTask;

    public ECBackgroundSwitcherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateAndInit(context);
    }

    public ECBackgroundSwitcherView(Context context) {
        super(context);
        inflateAndInit(context);
    }

    private void inflateAndInit(final Context context) {
        setChildrenDrawingOrderEnabled(true);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        bgImageGap = (displayMetrics.widthPixels / 100) * widthBackgroundImageGapPercent;
        bgImageWidth = displayMetrics.widthPixels + bgImageGap * 2;

        this.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView myView = new ImageView(context);
                myView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                myView.setLayoutParams(new FrameLayout.LayoutParams(bgImageWidth, FrameLayout.LayoutParams.MATCH_PARENT));
                myView.setTranslationX(-bgImageGap);
                return myView;
            }
        });

        bgImageInLeftAnimation = createBgImageInAnimation(bgImageGap, 0, movementDuration, alphaDuration);
        bgImageOutLeftAnimation = createBgImageOutAnimation(0, -bgImageGap, movementDuration);
        bgImageInRightAnimation = createBgImageInAnimation(-bgImageGap, 0, movementDuration, alphaDuration);
        bgImageOutRightAnimation = createBgImageOutAnimation(0, bgImageGap, movementDuration);
    }

//    public ECBackgroundSwitcherView withAnimationSettings(int movementDuration, int alphaDuration) {
//        this.movementDuration = movementDuration;
//        this.alphaDuration = alphaDuration;
//        bgImageInLeftAnimation = createBgImageInAnimation(bgImageGap, 0, movementDuration, alphaDuration);
//        bgImageOutLeftAnimation = createBgImageOutAnimation(0, -bgImageGap, movementDuration);
//        bgImageInRightAnimation = createBgImageInAnimation(-bgImageGap, 0, movementDuration, alphaDuration);
//        bgImageOutRightAnimation = createBgImageOutAnimation(0, bgImageGap, movementDuration);
//        return this;
//    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        if (reverseDrawOrder)
            return REVERSE_ORDER[i];
        else
            return NORMAL_ORDER[i];
    }

    public void setReverseDrawOrder(boolean reverseDrawOrder) {
        this.reverseDrawOrder = reverseDrawOrder;
    }

    private synchronized void setImageBitmapWithAnimation1(Bitmap newBitmap, AnimationDirection animationDirection, ECPager pager) {
//        if (this.currentAnimationDirection == animationDirection) {
//            this.setImageBitmap(newBitmap);
//        } else if (animationDirection == AnimationDirection.LEFT) {
//            this.setInAnimation(bgImageInLeftAnimation);
//            this.setOutAnimation(bgImageOutLeftAnimation);
//            this.setImageBitmap(newBitmap);
//        } else if (animationDirection == AnimationDirection.RIGHT) {
//            this.setInAnimation(bgImageInRightAnimation);
//            this.setOutAnimation(bgImageOutRightAnimation);
//            this.setImageBitmap(newBitmap);
//        }
        setImageBackgroundFromUrl(pager);


        this.currentAnimationDirection = animationDirection;
    }

    public void cacheBackgroundAtPosition(ECPager pager, int position) {
        if (position >= 0 && position < pager.getAdapter().getCount()) {
            Integer mainBgImageDrawableResource = pager.getDataFromAdapterDataset(position).getMainBackgroundResource();
            if (mainBgImageDrawableResource == null) return;
            BitmapWorkerTask addBitmapToCacheTask = new BitmapWorkerTask(getResources(), mainBgImageDrawableResource);
            addBitmapToCacheTask.execute(position);
        }
    }

    public void updateCurrentBackground(ECPager pager, final AnimationDirection direction) {
        int position = pager.getCurrentPosition();
        BackgroundBitmapCache instance = BackgroundBitmapCache.getInstance();
        Bitmap cachedBitmap = instance.getBitmapFromBgMemCache(position);
        if (cachedBitmap == null) {
            Integer mainBgImageDrawableResource = pager.getDataFromAdapterDataset(position).getMainBackgroundResource();
            if (mainBgImageDrawableResource == null) return;
            cachedBitmap = BitmapFactory.decodeResource(getResources(), mainBgImageDrawableResource, new BitmapFactoryOptions());
            instance.addBitmapToBgMemoryCache(position, cachedBitmap);
        }
        setImageBitmapWithAnimation1(cachedBitmap, direction, pager);
    }

    private synchronized void setImageBitmapWithAnimation(Bitmap newBitmap, AnimationDirection animationDirection) {
        if (this.currentAnimationDirection == animationDirection) {
            this.setImageBitmap(newBitmap);
        } else if (animationDirection == AnimationDirection.LEFT) {
            this.setInAnimation(bgImageInLeftAnimation);
            this.setOutAnimation(bgImageOutLeftAnimation);
            this.setImageBitmap(newBitmap);
        } else if (animationDirection == AnimationDirection.RIGHT) {
            this.setInAnimation(bgImageInRightAnimation);
            this.setOutAnimation(bgImageOutRightAnimation);
            this.setImageBitmap(newBitmap);
        }

        this.currentAnimationDirection = animationDirection;
    }

    private void setImageBackgroundFromUrl(ECPager pager) {
        Log.d("provera", "setImageBackgroundFromUrl: "+pager.getCurrentPosition());
        final ECBackgroundSwitcherView ecBackgroundSwitcherView = this;
        Picasso.with(getContext()).load(pager.getDataFromAdapterDataset(pager.getCurrentPosition()).getUrlImage()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                ecBackgroundSwitcherView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.d("provera", "onBitmapFailed: " + errorDrawable);

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    public void updateCurrentBackgroundAsync(final ECPager pager, final AnimationDirection direction) {
        if (mCurrentAnimationTask != null && mCurrentAnimationTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
            getInAnimation().cancel();
        }
        int position = pager.getCurrentPosition();
        Integer mainBgImageDrawableResource = pager.getDataFromAdapterDataset(position).getMainBackgroundResource();
        if (mainBgImageDrawableResource == null) return;
        mCurrentAnimationTask = new BitmapWorkerTask(getResources(), mainBgImageDrawableResource) {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                setImageBitmapWithAnimation1(bitmap, direction, pager);
            }
        };
        mCurrentAnimationTask.execute(position);
    }


    private void setImageBitmap(Bitmap bitmap) {
        ImageView image = (ImageView) this.getNextView();
        image.setImageBitmap(bitmap);
        showNext();
    }

    private Animation createBgImageInAnimation(int fromX, int toX, int transitionDuration, int alphaDuration) {
        TranslateAnimation translate = new TranslateAnimation(fromX, toX, 0, 0);
        translate.setDuration(transitionDuration);

        AlphaAnimation alpha = new AlphaAnimation(0F, 1F);
        alpha.setDuration(alphaDuration);

        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(translate);
        set.addAnimation(alpha);
        return set;
    }

    private Animation createBgImageOutAnimation(int fromX, int toX, int transitionDuration) {
        TranslateAnimation ta = new TranslateAnimation(fromX, toX, 0, 0);
        ta.setDuration(transitionDuration);
        ta.setInterpolator(new DecelerateInterpolator());
        return ta;
    }

    enum AnimationDirection {
        LEFT, RIGHT
    }
}