package eu.kanade.mangafeed.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.kanade.mangafeed.R;
import eu.kanade.mangafeed.data.models.Manga;
import eu.kanade.mangafeed.presenter.MangaCataloguePresenter;
import eu.kanade.mangafeed.view.MangaCatalogueView;

public class MangaCatalogueActivity extends BaseActivity implements MangaCatalogueView {

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Bind(R.id.manga_artist) TextView mArtist;
    @Bind(R.id.manga_author) TextView mAuthor;
    @Bind(R.id.manga_chapters) TextView mChapters;
    @Bind(R.id.manga_genres) TextView mGenres;
    @Bind(R.id.manga_status) TextView mStatus;
    @Bind(R.id.manga_summary) TextView mDescription;
    @Bind(R.id.manga_cover) ImageView mCover;

    private MangaCataloguePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_catalogue);
        ButterKnife.bind(this);

        setupToolbar(toolbar);

        presenter = new MangaCataloguePresenter(this);
        presenter.initialize();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.registerForStickyEvents();
    }

    @Override
    public void onStop() {
        presenter.unregisterForEvents();
        super.onStop();
    }

    // MangaCatalogueView

    @Override
    public void setTitle(String title) {
        setToolbarTitle(title);
    }

    @Override
    public void setMangaInformation(Manga manga) {
        mArtist.setText(manga.artist);
        mAuthor.setText(manga.author);
        mChapters.setText("0"); // TODO
        mGenres.setText(manga.genre);
        mStatus.setText("Ongoing"); //TODO
        mDescription.setText(manga.description);

        Glide.with(getActivity())
                .load(manga.thumbnail_url)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .centerCrop()
                .into(mCover);
    }


}