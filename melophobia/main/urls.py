from django.urls import path

from melophobia.main.views import CountryListView, CountryCreateView, CountryUpdateView, CountryDeleteView, \
    RegionListView, RegionCreateView, RegionUpdateView, RegionDeleteView, LocationListView, LocationCreateView, \
    LocationUpdateView, LocationDeleteView, GenreListView, GenreCreateView, GenreUpdateView, GenreDeleteView, \
    LabelListView, LabelCreateView, LabelUpdateView, LabelDeleteView, ArtistListView, ArtistCreateView, \
    ArtistUpdateView, ArtistDeleteView, MediaListView, MediaCreateView, MediaUpdateView, MediaDeleteView, \
    LanguageListView, LanguageCreateView, LanguageUpdateView, LanguageDeleteView, ProducerListView, ProducerCreateView, \
    ProducerUpdateView, ProducerDeleteView, ReleaseListView, ReleaseCreateView, ReleaseUpdateView, ReleaseDeleteView, \
    IssueListView, IssueCreateView, IssueUpdateView, IssueDeleteView, CollectionListView, CollectionCreateView, \
    CollectionUpdateView, CollectionDeleteView, ArtistDetailView, LabelDetailView, ProducerDetailView

urlpatterns = [
    path('artist/list', ArtistListView.as_view(), name='artist_list'),
    path('artist/add', ArtistCreateView.as_view(), name='artist_add'),
    path('artist/info/<int:pk>', ArtistDetailView.as_view(), name='artist_info'),
    path('artist/edit/<int:pk>', ArtistUpdateView.as_view(), name='artist_edit'),
    path('artist/delete/<int:pk>', ArtistDeleteView.as_view(), name='artist_delete'),
    path('collection/list', CollectionListView.as_view(), name='collection_list'),
    path('collection/add', CollectionCreateView.as_view(), name='collection_add'),
    path('collection/edit/<int:pk>', CollectionUpdateView.as_view(), name='collection_edit'),
    path('collection/delete/<int:pk>', CollectionDeleteView.as_view(), name='collection_delete'),
    path('country/list', CountryListView.as_view(), name='country_list'),
    path('country/add', CountryCreateView.as_view(), name='country_add'),
    path('country/edit/<int:pk>', CountryUpdateView.as_view(), name='country_edit'),
    path('country/delete/<int:pk>', CountryDeleteView.as_view(), name='country_delete'),
    path('genre/list', GenreListView.as_view(), name='genre_list'),
    path('genre/add', GenreCreateView.as_view(), name='genre_add'),
    path('genre/edit/<int:pk>', GenreUpdateView.as_view(), name='genre_edit'),
    path('genre/delete/<int:pk>', GenreDeleteView.as_view(), name='genre_delete'),
    path('issue/list', IssueListView.as_view(), name='issue_list'),
    path('issue/add', IssueCreateView.as_view(), name='issue_add'),
    path('issue/edit/<int:pk>', IssueUpdateView.as_view(), name='issue_edit'),
    path('issue/delete/<int:pk>', IssueDeleteView.as_view(), name='issue_delete'),
    path('label/list', LabelListView.as_view(), name='label_list'),
    path('label/add', LabelCreateView.as_view(), name='label_add'),
    path('label/info/<int:pk>', LabelDetailView.as_view(), name='label_info'),
    path('label/edit/<int:pk>', LabelUpdateView.as_view(), name='label_edit'),
    path('label/delete/<int:pk>', LabelDeleteView.as_view(), name='label_delete'),
    path('language/list', LanguageListView.as_view(), name='language_list'),
    path('language/add', LanguageCreateView.as_view(), name='language_add'),
    path('language/edit/<int:pk>', LanguageUpdateView.as_view(), name='language_edit'),
    path('language/delete/<int:pk>', LanguageDeleteView.as_view(), name='language_delete'),
    path('location/list', LocationListView.as_view(), name='location_list'),
    path('location/add', LocationCreateView.as_view(), name='location_add'),
    path('location/edit/<int:pk>', LocationUpdateView.as_view(), name='location_edit'),
    path('location/delete/<int:pk>', LocationDeleteView.as_view(), name='location_delete'),
    path('media/list', MediaListView.as_view(), name='media_list'),
    path('media/add', MediaCreateView.as_view(), name='media_add'),
    path('media/edit/<int:pk>', MediaUpdateView.as_view(), name='media_edit'),
    path('media/delete/<int:pk>', MediaDeleteView.as_view(), name='media_delete'),
    path('producer/list', ProducerListView.as_view(), name='producer_list'),
    path('producer/add', ProducerCreateView.as_view(), name='producer_add'),
    path('producer/info/<int:pk>', ProducerDetailView.as_view(), name='producer_info'),
    path('producer/edit/<int:pk>', ProducerUpdateView.as_view(), name='producer_edit'),
    path('producer/delete/<int:pk>', ProducerDeleteView.as_view(), name='producer_delete'),
    path('region/list', RegionListView.as_view(), name='region_list'),
    path('region/add', RegionCreateView.as_view(), name='region_add'),
    path('region/edit/<int:pk>', RegionUpdateView.as_view(), name='region_edit'),
    path('region/delete/<int:pk>', RegionDeleteView.as_view(), name='region_delete'),
    path('release/list', ReleaseListView.as_view(), name='release_list'),
    path('release/add', ReleaseCreateView.as_view(), name='release_add'),
    path('release/edit/<int:pk>', ReleaseUpdateView.as_view(), name='release_edit'),
    path('release/delete/<int:pk>', ReleaseDeleteView.as_view(), name='release_delete')
]
