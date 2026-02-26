from django.contrib.auth.views import LoginView, LogoutView
from django.urls import path

from melophobia.main.views import CountryListView, CountryCreateView, CountryUpdateView, CountryDeleteView, \
    RegionListView, RegionCreateView, RegionUpdateView, RegionDeleteView, LocationListView, LocationCreateView, \
    LocationUpdateView, LocationDeleteView, GenreListView, GenreCreateView, GenreUpdateView, GenreDeleteView

urlpatterns = [
    path('country/list', CountryListView.as_view(), name='country_list'),
    path('country/add', CountryCreateView.as_view(), name='country_add'),
    path('country/edit/<int:pk>', CountryUpdateView.as_view(), name='country_edit'),
    path('country/delete/<int:pk>', CountryDeleteView.as_view(), name='country_delete'),
    path('genre/list', GenreListView.as_view(), name='genre_list'),
    path('genre/add', GenreCreateView.as_view(), name='genre_add'),
    path('genre/edit/<int:pk>', GenreUpdateView.as_view(), name='genre_edit'),
    path('genre/delete/<int:pk>', GenreDeleteView.as_view(), name='genre_delete'),
    path('location/list', LocationListView.as_view(), name='location_list'),
    path('location/add', LocationCreateView.as_view(), name='location_add'),
    path('location/edit/<int:pk>', LocationUpdateView.as_view(), name='location_edit'),
    path('location/delete/<int:pk>', LocationDeleteView.as_view(), name='location_delete'),
    path('region/list', RegionListView.as_view(), name='region_list'),
    path('region/add', RegionCreateView.as_view(), name='region_add'),
    path('region/edit/<int:pk>', RegionUpdateView.as_view(), name='region_edit'),
    path('region/delete/<int:pk>', RegionDeleteView.as_view(), name='region_delete')
]
