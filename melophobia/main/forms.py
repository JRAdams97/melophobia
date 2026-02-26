from django import forms

from melophobia.main.models import Country, Region


class CountryForm(forms.ModelForm):
    class Meta:
        model = Country
        fields = ['name', 'alpha2_code']
        labels = {
            'name': 'Name',
            'alpha2_code': 'Alpha-2 Code'
        }


class RegionForm(forms.ModelForm):
    class Meta:
        model = Region
        fields = ['name', 'abbreviation', 'country']
        labels = {
            'name': 'Name',
            'abbreviation': 'Abbreviation',
            'country': 'Country'
        }
