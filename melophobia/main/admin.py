from django.contrib import admin

from melophobia.main.models import Location, Region, Country

admin.site.register(Country)
admin.site.register(Region)
admin.site.register(Location)
