from googletrans import Translator

def translate_text(text,dest):
    try:
        translator = Translator()
        translation = translator.translate(text,dest=dest)
        return translation.text
    except Exception as e:
        return e