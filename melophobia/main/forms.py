from django import forms
from django.forms import inlineformset_factory

from melophobia.main.models import Country, Region, Location, Genre, Label, Artist, Media, Language, Producer, Release, \
    Issue, IssueVariant, ReleaseTypeValue

FAVOURITE_LABEL = 'Favourite?'
SORT_NAME_LABEL = 'Sort Name'


class ArtistForm(forms.ModelForm):
    class Meta:
        model = Artist
        fields = ['name', 'sort_name', 'type', 'gender', 'formation_year', 'formation_location', 'disband_year',
                  'genres', 'is_favourite']
        labels = {
            'name': 'Name',
            'sort_name': SORT_NAME_LABEL,
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
            'sort_name': SORT_NAME_LABEL,
            'formation_year': 'Formation Year',
            'formation_location': 'Formation Location',
            'closure_year': 'Closure Year',
            'is_favourite': FAVOURITE_LABEL,
            'labelcode': 'Labelcode',
            'type': 'Type'
        }


class LanguageForm(forms.ModelForm):
    class Meta:
        model = Language
        fields = ['name', 'script']


class LocationForm(forms.ModelForm):
    class Meta:
        model = Location
        fields = ['name', 'region', 'latitude', 'longitude']


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


class ProducerForm(forms.ModelForm):
    class Meta:
        model = Producer
        fields = ['name', 'sort_name', 'gender', 'birth_date', 'birth_location', 'death_date', 'is_favourite']
        labels = {
            'name': 'Name',
            'sort_name': SORT_NAME_LABEL,
            'gender': 'Gender',
            'birth_date': 'Birth Date',
            'birth_location': 'Birth Location',
            'death_date': 'Death Date',
            'is_favourite': FAVOURITE_LABEL
        }


class RegionForm(forms.ModelForm):
    class Meta:
        model = Region
        fields = ['name', 'abbreviation', 'country']


class ReleaseForm(forms.ModelForm):
    class Meta:
        model = Release
        widgets = {
            'types': forms.CheckboxSelectMultiple
        }
        fields = ['title', 'alternate_title', 'release_date', 'artists', 'genres', 'is_favourite', 'languages',
                  'producers', 'aoty_rank', 'bea_rank', 'christgau_rating', 'metacritic_rating', 'rym_rank',
                  'rym_rating', 'is_official', 'types']
        labels = {
            'title': 'Title',
            'alternate_title': 'Alternate Title',
            'release_date': 'Release Date',
            'artists': 'Artists',
            'genres': 'Genres',
            'is_favourite': FAVOURITE_LABEL,
            'languages': 'Languages',
            'producers': 'Producers',
            'aoty_rank': 'AOTY Rank',
            'bea_rank': 'BEA Rank',
            'christgau_rating': 'Christgau Rating',
            'metacritic_rating': 'Metacritic Rating',
            'rym_rank': 'RYM Rank',
            'rym_rating': 'RYM Rating',
            'is_official': 'Is Official?',
            'types': 'Types'
        }

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)

        # Override label generation for release types
        self.fields['types'].label_from_instance = self.get_release_type_label

    @staticmethod
    def get_release_type_label(obj):
        # Match DB string (obj.type) to ReleaseTypeValue label
        try:
            return ReleaseTypeValue[obj.type].label
        except KeyError:
            return obj.type # Fallback


class IssueForm(forms.ModelForm):
    class Meta:
        model = Issue
        fields = ['parent_release', 'label', 'release_year', 'release_country', 'catalogue_number', 'media', 'edition',
                  'is_reissue', 'is_official', 'is_promo', 'has_pregap', 'has_data_track', 'barcode', 'comment']
        labels = {
            'parent_release': 'Release',
            'label': 'Label',
            'release_year': 'Release Year',
            'release_country': 'Release Country',
            'catalogue_number': 'Catalogue #',
            'media': 'Media',
            'edition': 'Edition',
            'is_reissue': 'Reissue?',
            'is_official': 'Official?',
            'is_promo': 'Promo?',
            'has_pregap': 'Has Pregap?',
            'has_data_track': 'Has Data Track(s)?',
            'barcode': 'Barcode'
        }

IssueVariantFormSet = inlineformset_factory(
    Issue,
    IssueVariant,
    fields = ['matrix_runout', 'master_sid', 'mould_sid', 'spars_code', 'comment'],
    labels = {
        'matrix_runout': 'Matrix/Runout',
        'master_sid': 'Mastering SID',
        'mould_sid': 'Mould SID',
        'spars_code': 'SPARS Code',
        'comment': 'Variant Comment'
    },
    extra = 1,
    can_delete = True
)
