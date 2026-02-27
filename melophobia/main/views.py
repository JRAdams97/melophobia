from django.db.models import QuerySet
from django.urls import reverse_lazy
from django.views.generic import ListView, CreateView, UpdateView, DeleteView

from melophobia.main.forms import CountryForm, RegionForm, LocationForm, GenreForm, LabelForm
from melophobia.main.models import Country, Region, Location, Genre, Label


class CountryCreateView(CreateView):
    model = Country
    form_class = CountryForm
    template_name = 'country/form.html'
    success_url = reverse_lazy('country_list')


class CountryListView(ListView):
    model = Country
    template_name = 'country/list.html'
    context_object_name = 'countries'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return Country.objects.all()


class CountryUpdateView(UpdateView):
    model = Country
    form_class = CountryForm
    template_name = 'country/form.html'
    success_url = reverse_lazy('country_list')


class CountryDeleteView(DeleteView):
    model = Country
    success_url = reverse_lazy('country_list')


class GenreCreateView(CreateView):
    model = Genre
    form_class = GenreForm
    template_name = 'genre/form.html'
    success_url = reverse_lazy('genre_list')


class GenreListView(ListView):
    model = Genre
    template_name = 'genre/list.html'
    context_object_name = 'genres'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return Genre.objects.all()


class GenreUpdateView(UpdateView):
    model = Genre
    form_class = GenreForm
    template_name = 'genre/form.html'
    success_url = reverse_lazy('genre_list')


class GenreDeleteView(DeleteView):
    model = Genre
    success_url = reverse_lazy('genre_list')


class LabelCreateView(CreateView):
    model = Label
    form_class = LabelForm
    template_name = 'label/form.html'
    success_url = reverse_lazy('label_list')


class LabelListView(ListView):
    model = Label
    template_name = 'label/list.html'
    context_object_name = 'labels'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return Label.objects.all()


class LabelUpdateView(UpdateView):
    model = Label
    form_class = LabelForm
    template_name = 'label/form.html'
    success_url = reverse_lazy('label_list')


class LabelDeleteView(DeleteView):
    model = Label
    success_url = reverse_lazy('label_list')


class LocationCreateView(CreateView):
    model = Location
    form_class = LocationForm
    template_name = 'location/form.html'
    success_url = reverse_lazy('location_list')


class LocationListView(ListView):
    model = Location
    template_name = 'location/list.html'
    context_object_name = 'locations'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return Location.objects.all()


class LocationUpdateView(UpdateView):
    model = Location
    form_class = LocationForm
    template_name = 'location/form.html'
    success_url = reverse_lazy('location_list')


class LocationDeleteView(DeleteView):
    model = Location
    success_url = reverse_lazy('location_list')


class RegionCreateView(CreateView):
    model = Region
    form_class = RegionForm
    template_name = 'region/form.html'
    success_url = reverse_lazy('region_list')


class RegionListView(ListView):
    model = Region
    template_name = 'region/list.html'
    context_object_name = 'regions'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return Region.objects.all()


class RegionUpdateView(UpdateView):
    model = Region
    form_class = RegionForm
    template_name = 'region/form.html'
    success_url = reverse_lazy('region_list')


class RegionDeleteView(DeleteView):
    model = Region
    success_url = reverse_lazy('region_list')