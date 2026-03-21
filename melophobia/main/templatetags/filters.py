from django import template

register = template.Library()


@register.filter
def format_enum(value):
    """
    Replaces all occurrences of '_' with a single space character.
    """
    return value.replace('_', ' ')  # Replace 'arg' with an empty string


@register.filter
def format_partial_date(value):
    """
    Convert 'YYYY/MM/DD' where MM or DD may be '00' into a readable partial
    date.
    """
    if not value:
        return ''

    parts = value.split('/')
    year, month, day = parts

    if month == '00':
        return year

    if day == '00':
        return f"{year}/{month}"

    return f"{year}/{month}/{day}"
