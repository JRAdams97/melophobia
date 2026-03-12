import os

from django.conf import settings


def get_artist_images():
    image_dir = os.path.join(settings.BASE_DIR, "melophobia/static/image/artist")
    files = sorted(os.listdir(image_dir))

    return [(f, f) for f in files if f.lower().endswith(("png", "jpg", "jpeg", "webp"))]
