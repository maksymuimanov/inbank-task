import {Field, FieldLabel} from "@/components/ui/field.tsx";
import {InputOTP, InputOTPGroup, InputOTPSlot} from "@/components/ui/input-otp.tsx";
import {REGEXP_ONLY_DIGITS} from "input-otp";

export const PersonalCodeInputField = () => {
    return (
        <Field>
            <FieldLabel htmlFor="personalCode">Personal Code</FieldLabel>
            <InputOTP name="personalCode" id="personalCode" minLength={11} maxLength={11} pattern={REGEXP_ONLY_DIGITS} required>
                <InputOTPGroup className="flex w-full">
                    {Array.from({length: 11}).map((_, i) => (
                        <InputOTPSlot key={i} index={i} className="h-16 flex-1"/>
                    ))}
                </InputOTPGroup>
            </InputOTP>
        </Field>
    );
};