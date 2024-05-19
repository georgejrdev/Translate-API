from googletrans import Translator


def translate_text(text,dest):
    if (len(text) <=0):
        return "Input is empty"
    
    try:
        translator = Translator()
        translation = translator.translate(text,dest=dest)
        return translation.text
    except Exception as e:
        return e