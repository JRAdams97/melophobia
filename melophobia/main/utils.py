import os

from django.conf import settings


def get_images(context):
    image_dir = os.path.join(settings.BASE_DIR, f"melophobia/static/image/{context}")
    files = sorted(os.listdir(image_dir))

    return [(f, f) for f in files if f.lower().endswith(("png", "jpg", "jpeg", "webp"))]
