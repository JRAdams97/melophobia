from django import forms

from melophobia.main.models import Country


class CountryForm(forms.ModelForm):
    class Meta:
        model = Country
        fields = ['name', 'alpha2_code']
        labels = {
            'name': 'Name',
            'alpha2_code': 'Alpha-2 Code'
        }