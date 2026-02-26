from django import forms

from melophobia.main.models import Country, Region, Location, Genre


class CountryForm(forms.ModelForm):
    class Meta:
        model = Country
        fields = ['name', 'alpha2_code']
        labels = {
            'name': 'Name',
            'alpha2_code': 'Alpha-2 Code'
        }


class GenreForm(forms.ModelForm):
    class Meta:
        model = Genre
        fields = ['name', 'parent_genres', 'origin_year', 'is_favourite']
        labels = {
            'name': 'Name',
            'parent_genres': 'Parent Genres',
            'origin_year': 'Origin Year',
            'is_favourite': 'Favourite?'
        }


class RegionForm(forms.ModelForm):
    class Meta:
        model = Region
        fields = ['name', 'abbreviation', 'country']


class LocationForm(forms.ModelForm):
    class Meta:
        model = Location
        fields = ['name', 'region', 'latitude', 'longitude']