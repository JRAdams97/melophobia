from django import forms

from melophobia.main.models import Country, Region, Location, Genre, Label, Artist, Media

FAVOURITE_LABEL = 'Favourite?'

class ArtistForm(forms.ModelForm):
    class Meta:
        model = Artist
        fields = ['name', 'sort_name', 'type', 'gender', 'formation_year', 'formation_location', 'disband_year',
                  'genres', 'is_favourite']
        labels = {
            'name': 'Name',
            'sort_name': 'Sort Name',
            'type': 'Type',
            'gender': 'Gender',
            'formation_year': 'Formation Year',
            'formation_location': 'Formation Location',
            'disband_year': 'Disband Year',
            'genres': 'Genres',
            'is_favourite': FAVOURITE_LABEL
        }


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
            'is_favourite': FAVOURITE_LABEL
        }


class LabelForm(forms.ModelForm):
    class Meta:
        model = Label
        fields = ['name', 'sort_name', 'formation_year', 'formation_location', 'closure_year', 'is_favourite',
                  'labelcode', 'type']
        labels = {
            'name': 'Name',
            'sort_name': 'Sort Name',
            'formation_year': 'Formation Year',
            'formation_location': 'Formation Location',
            'closure_year': 'Closure Year',
            'is_favourite': FAVOURITE_LABEL,
            'labelcode': 'Labelcode',
            'type': 'Type'
        }


class MediaForm(forms.ModelForm):
    class Meta:
        model = Media
        fields = ['name', 'abbreviation', 'classification', 'origin_year']
        labels = {
            'name': 'Name',
            'abbreviation': 'Abbreviation',
            'classification': 'Classification',
            'origin_year': 'Origin Year'
        }


class RegionForm(forms.ModelForm):
    class Meta:
        model = Region
        fields = ['name', 'abbreviation', 'country']


class LocationForm(forms.ModelForm):
    class Meta:
        model = Location
        fields = ['name', 'region', 'latitude', 'longitude']
