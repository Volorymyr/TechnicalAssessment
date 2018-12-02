package com.symphony.technicalassessment.screen.detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.symphony.technicalassessment.R;
import com.symphony.technicalassessment.data.product.model.Product;
import com.symphony.technicalassessment.screen.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity {

    private static final String EXTRA_PRODUCT = "extra_product";

    public static void start(@NonNull final Activity activity,
                             @NonNull final Product product,
                             @NonNull final View... sharedViews) {
        final Intent sharedIntent = new Intent(activity, DetailsActivity.class);
        sharedIntent.putExtra(EXTRA_PRODUCT, product);

        final String imageTransitionName = activity.getString(R.string.product_image_transition_name);
        final String nameTransitionName = activity.getString(R.string.product_name_transition_name);

        final Pair<View, String> namePair = Pair.create(sharedViews[0], nameTransitionName);
        final Pair<View, String> imagePair = Pair.create(sharedViews[1], imageTransitionName);

        final ActivityOptions activityOptions =
                ActivityOptions.makeSceneTransitionAnimation(activity, namePair, imagePair);

        activity.startActivity(sharedIntent, activityOptions.toBundle());
    }

    @BindView(R.id.details_image)
    ImageView mProductImageView;
    @BindView(R.id.details_name)
    TextView mNameTextView;
    @BindView(R.id.details_amount)
    TextView mAmountTextView;
    @BindView(R.id.details_description)
    TextView mDescriptionTextView;

    private final RequestOptions requestOptions = new RequestOptions();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        final Product product = getIntent().getParcelableExtra(EXTRA_PRODUCT);
        setUp(product);
    }

    @SuppressLint("CheckResult")
    private void setUp(@NonNull final Product product) {
        requestOptions.error(R.drawable.ic_error_placeholder);
        Glide.with(mProductImageView)
                .setDefaultRequestOptions(requestOptions)
                .load(product.getImageFullUrl())
                .into(mProductImageView);
        mNameTextView.setText(product.getName());
        mAmountTextView.setText(product.getSalePrice().getPrice());
        if (product.getDescription().isEmpty()) {
            mDescriptionTextView.setText(getString(R.string.details_description_empty));
        } else {
            mDescriptionTextView.setText(product.getDescription());
        }
    }
}
