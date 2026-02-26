from django.db.models import QuerySet
from django.urls import reverse_lazy
from django.views.generic import ListView, CreateView, UpdateView, DeleteView

from melophobia.main.forms import CountryForm
from melophobia.main.models import Country


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
