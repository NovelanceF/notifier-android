package com.lance.notifier.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.lance.notifier.R;

/**
 * Created by Novelance on 2/20/14.
 */
public class Footer {

  protected View mFooter;

  public static enum State {
    Idle, TheEnd, Loading
  }

  protected State mState = State.Idle;

  private ProgressBar mProgress;

  public Footer(Context context) {
    mFooter = LayoutInflater.from(context).inflate(R.layout.footer, null);
    mFooter.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    mProgress = (ProgressBar)mFooter.findViewById(R.id.progressbar_footer);
    setState(State.Idle);
  }

  public View getView() {
    return mFooter;
  }

  public State getState() {
    return mState;
  }

  public void setState(final State state, long delay) {
    mFooter.postDelayed(new Runnable() {

      @Override
      public void run() {
        setState(state);
      }
    }, delay);
  }

  public void setState(State status) {
    if (mState == status) {
      return;
    }
    mState = status;

    mFooter.setVisibility(View.VISIBLE);

    switch (status) {
      case Loading:
        mProgress.setVisibility(View.VISIBLE);
        break;
      case TheEnd:
        mProgress.setVisibility(View.GONE);
        break;
      default:
        break;
    }
  }
}
