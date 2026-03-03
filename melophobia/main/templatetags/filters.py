from django import template

register = template.Library()

@register.filter
def format_enum(value):
    """
    Replaces all occurrences of '_' with a single space character.
    """
    return value.replace("_", " ") # Replace 'arg' with an empty string
