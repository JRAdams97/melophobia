from django.contrib.auth.views import LoginView, LogoutView
from django.urls import path

from melophobia.main.views import CountryListView, CountryCreateView, CountryUpdateView, CountryDeleteView

urlpatterns = [
    path('country/list', CountryListView.as_view(), name='country_list'),
    path('country/add', CountryCreateView.as_view(), name='country_add'),
    path('country/edit/<int:pk>', CountryUpdateView.as_view(), name='country_edit'),
    path('country/delete/<int:pk>', CountryDeleteView.as_view(), name='country_delete')
]
