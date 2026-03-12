from django.db.models import QuerySet
from django.urls import reverse_lazy
from django.views.generic import ListView, CreateView, UpdateView, DeleteView, DetailView

from melophobia.main.forms import CountryForm, RegionForm, LocationForm, GenreForm, LabelForm, ArtistForm, MediaForm, \
    LanguageForm, ProducerForm, ReleaseForm, IssueVariantFormSet, IssueForm, CollectionItemForm
from melophobia.main.models import Country, Region, Location, Genre, Label, Artist, Media, Language, Producer, Release, \
    Issue, ReleaseType, CollectionItem


class ArtistCreateView(CreateView):
    model = Artist
    form_class = ArtistForm
    template_name = 'artist/form.html'
    success_url = reverse_lazy('artist_list')


class ArtistDetailView(DetailView):
    model = Artist
    template_name = 'artist/info.html'
    context_object_name = 'artist'


class ArtistListView(ListView):
    model = Artist
    template_name = 'artist/list.html'
    context_object_name = 'artists'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return Artist.objects.all()


class ArtistUpdateView(UpdateView):
    model = Artist
    form_class = ArtistForm
    template_name = 'artist/form.html'
    success_url = reverse_lazy('artist_list')


class ArtistDeleteView(DeleteView):
    model = Artist
    success_url = reverse_lazy('artist_list')


class CollectionCreateView(CreateView):
    model = CollectionItem
    form_class = CollectionItemForm
    template_name = 'collection/form.html'
    success_url = reverse_lazy('collection_list')


class CollectionListView(ListView):
    model = CollectionItem
    template_name = 'collection/list.html'
    context_object_name = 'collection'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return CollectionItem.objects.all()

class CollectionUpdateView(UpdateView):
    model = CollectionItem
    form_class = CollectionItemForm
    template_name = 'collection/form.html'
    success_url = reverse_lazy('collection_list')


class CollectionDeleteView(DeleteView):
    model = CollectionItem
    success_url = reverse_lazy('collection_list')


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


class IssueCreateView(CreateView):
    model = Issue
    form_class = IssueForm
    template_name = 'issue/form.html'
    success_url = reverse_lazy('issue_list')

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)

        if self.request.POST:
            context['issue_variant_formset'] = IssueVariantFormSet(self.request.POST)
        else:
            context['issue_variant_formset'] = IssueVariantFormSet()

        return context

    def form_valid(self, form):
        context = self.get_context_data()
        issue_variant_formset = context["issue_variant_formset"]

        if issue_variant_formset.is_valid():
            self.object = form.save()
            issue_variant_formset.instance = self.object
            issue_variant_formset.save()

            return super().form_valid(form)

        else:
            return self.form_invalid(form)


class IssueListView(ListView):
    model = Issue
    template_name = 'issue/list.html'
    context_object_name = 'issues'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return Issue.objects.all()


class IssueUpdateView(UpdateView):
    model = Issue
    form_class = IssueVariantFormSet
    template_name = 'issue/form.html'
    success_url = reverse_lazy('issue_list')

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)

        if self.request.POST:
            context["variant_formset"] = IssueVariantFormSet(self.request.POST)
        else:
            context["variant_formset"] = IssueVariantFormSet()

        return context

    def form_valid(self, form):
        context = self.get_context_data()
        variant_formset = context["variant_formset"]

        if variant_formset.is_valid():
            self.object = form.save()
            variant_formset.instance = self.object
            variant_formset.save()

            return super().form_valid(form)

        else:
            return self.form_invalid(form)


class IssueDeleteView(DeleteView):
    model = Issue
    success_url = reverse_lazy('issue_list')


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


class LanguageCreateView(CreateView):
    model = Language
    form_class = LanguageForm
    template_name = 'language/form.html'
    success_url = reverse_lazy('language_list')


class LanguageListView(ListView):
    model = Language
    template_name = 'language/list.html'
    context_object_name = 'languages'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return Language.objects.all()


class LanguageUpdateView(UpdateView):
    model = Language
    form_class = LanguageForm
    template_name = 'language/form.html'
    success_url = reverse_lazy('language_list')


class LanguageDeleteView(DeleteView):
    model = Language
    success_url = reverse_lazy('language_list')


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


class MediaCreateView(CreateView):
    model = Media
    form_class = MediaForm
    template_name = 'media/form.html'
    success_url = reverse_lazy('media_list')


class MediaListView(ListView):
    model = Media
    template_name = 'media/list.html'
    context_object_name = 'media_list'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return Media.objects.all()


class MediaUpdateView(UpdateView):
    model = Media
    form_class = MediaForm
    template_name = 'media/form.html'
    success_url = reverse_lazy('media_list')


class MediaDeleteView(DeleteView):
    model = Location
    success_url = reverse_lazy('media_list')


class ProducerCreateView(CreateView):
    model = Producer
    form_class = ProducerForm
    template_name = 'producer/form.html'
    success_url = reverse_lazy('producer_list')


class ProducerListView(ListView):
    model = Producer
    template_name = 'producer/list.html'
    context_object_name = 'producers'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return Producer.objects.all()


class ProducerUpdateView(UpdateView):
    model = Producer
    form_class = ProducerForm
    template_name = 'producer/form.html'
    success_url = reverse_lazy('producer_list')


class ProducerDeleteView(DeleteView):
    model = Producer
    success_url = reverse_lazy('producer_list')


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


class ReleaseCreateView(CreateView):
    model = Release
    form_class = ReleaseForm
    template_name = 'release/form.html'
    success_url = reverse_lazy('release_list')


class ReleaseListView(ListView):
    model = Release
    template_name = 'release/list.html'
    context_object_name = 'releases'
    paginate_by = 25

    def get_queryset(self) -> QuerySet:
        return Release.objects.all()


class ReleaseUpdateView(UpdateView):
    model = Release
    form_class = ReleaseForm
    template_name = 'release/form.html'
    success_url = reverse_lazy('release_list')


class ReleaseDeleteView(DeleteView):
    model = Release
    success_url = reverse_lazy('release_list')