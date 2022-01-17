package apps.hungtrquoc.quicknotes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import apps.hungtrquoc.quicknotes.R;
import apps.hungtrquoc.quicknotes.db.OpenHelper;
import apps.hungtrquoc.quicknotes.fragment.CategoryFragment;
import apps.hungtrquoc.quicknotes.fragment.template.RecyclerFragment;
import apps.hungtrquoc.quicknotes.inner.Animator;
import apps.hungtrquoc.quicknotes.model.Category;

public class CategoryActivity extends AppCompatActivity implements RecyclerFragment.Callbacks {
	public static final int REQUEST_CODE = 1;
	public static final int RESULT_CHANGE = 101;
	private Toolbar toolbar;
	private CategoryFragment fragment;

	private AdView banner;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setTheme(Category.getStyle(getIntent().getIntExtra(OpenHelper.COLUMN_THEME, Category.THEME_GREEN)));
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		try {
			//noinspection ConstantConditions
			getSupportActionBar().setDisplayShowTitleEnabled(false);
		} catch (Exception ignored) {
		}

		toolbar.findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});

		if (savedInstanceState == null) {
			fragment = new CategoryFragment();

			getSupportFragmentManager().beginTransaction()
				.add(R.id.container, fragment)
				.commit();
		}
		requestAds();
	}

	private void requestAds(){
		MobileAds.initialize(getApplicationContext(), getApplicationContext().getString(R.string.banner_id));
		banner = (AdView)findViewById(R.id.banner);
		AdRequest adRequest = new AdRequest.Builder().build();
		banner.loadAd(adRequest);
	}


	@Override
	public void onChangeSelection(boolean state) {
		if (state) {
			Animator.create(getApplicationContext())
				.on(toolbar)
				.setEndVisibility(View.INVISIBLE)
				.animate(R.anim.fade_out);
		} else {
			Animator.create(getApplicationContext())
				.on(toolbar)
				.setStartVisibility(View.VISIBLE)
				.animate(R.anim.fade_in);
		}
	}

	@Override
	public void toggleOneSelection(boolean state) {
	}

	@Override
	public void onBackPressed() {
		if (fragment.isFabOpen) {
			fragment.toggleFab(true);
			return;
		}

		if (fragment.selectionState) {
			fragment.toggleSelection(false);
			return;
		}

		Intent data = new Intent();
		data.putExtra("position", fragment.categoryPosition);
		data.putExtra(OpenHelper.COLUMN_COUNTER, fragment.items.size());
		setResult(RESULT_CHANGE, data);
		finish();
	}
}
