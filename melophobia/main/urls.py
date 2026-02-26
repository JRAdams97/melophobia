from django.contrib.auth.views import LoginView, LogoutView
from django.urls import path

from melophobia.main.views import CountryListView, CountryCreateView, CountryUpdateView, CountryDeleteView, \
    RegionListView, RegionCreateView, RegionUpdateView, RegionDeleteView

urlpatterns = [
    path('country/list', CountryListView.as_view(), name='country_list'),
    path('country/add', CountryCreateView.as_view(), name='country_add'),
    path('country/edit/<int:pk>', CountryUpdateView.as_view(), name='country_edit'),
    path('country/delete/<int:pk>', CountryDeleteView.as_view(), name='country_delete'),
    path('region/list', RegionListView.as_view(), name='region_list'),
    path('region/add', RegionCreateView.as_view(), name='region_add'),
    path('region/edit/<int:pk>', RegionUpdateView.as_view(), name='region_edit'),
    path('region/delete/<int:pk>', RegionDeleteView.as_view(), name='region_delete')
]
