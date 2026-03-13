import {Field, FieldGroup, FieldLabel} from "@/components/ui/field.tsx";
import {Button} from "@/components/ui/button.tsx";
import type {LoanDecisionResponse} from "@/types/types.ts";
import {postRequest} from "@/utils/forms.ts";
import {Leapfrog} from 'ldrs/react'
import 'ldrs/react/Leapfrog.css'
import {InputGroup, InputGroupAddon, InputGroupInput, InputGroupText} from "@/components/ui/input-group.tsx";
import {InputOTP, InputOTPGroup, InputOTPSlot} from "@/components/ui/input-otp.tsx";
import {REGEXP_ONLY_DIGITS} from "input-otp";
import * as React from "react";
import {useState} from "react";
import {Slider} from "@/components/ui/slider.tsx";

export const LoanDecisionForm = (
    {
        setResponse,
        loading,
        setLoading,
        setErrorMessage
    }: {
        setResponse: React.Dispatch<React.SetStateAction<LoanDecisionResponse | undefined>>,
        loading: boolean,
        setLoading: React.Dispatch<React.SetStateAction<boolean>>,
        setErrorMessage: React.Dispatch<React.SetStateAction<string>>
    }
) => {
    const [period, setPeriod] = useState<number>(12);
    const [amount, setAmount] = useState<number>(2000);

    return (
        <form onSubmit={(e) => {
            e.preventDefault()
            const formData = new FormData(e.currentTarget)
            postRequest(formData, setResponse, setLoading, setErrorMessage)
        }}>
            <FieldGroup>
                <Field>
                    <FieldLabel htmlFor="personalCode">Personal Code</FieldLabel>
                    <InputOTP name="personalCode" id="personalCode" maxLength={11} defaultValue="49002010965" pattern={REGEXP_ONLY_DIGITS} required>
                        <InputOTPGroup className="flex w-full">
                            {Array.from({length: 11}).map((_, i) => (
                                <InputOTPSlot key={i} index={i} className="h-16 flex-1"/>
                            ))}
                        </InputOTPGroup>
                    </InputOTP>
                </Field>
                <Field>
                    <div className="flex items-center">
                        <FieldLabel htmlFor="amount">Loan amount</FieldLabel>
                    </div>
                    <div className="flex items-center gap-5">
                        <div className="w-full pt-7">
                            <Slider step={100} min={2000} max={10000} value={amount} onValueChange={value => setAmount(value as number)} />
                            <div className="flex items-center justify-between gap-2 text-sm text-muted-foreground mt-3.5">
                                <span>2000</span>
                                <span>10000</span>
                            </div>
                        </div>
                        <InputGroup className="h-16 rounded-[1vw]">
                            <InputGroupAddon className="pr-2 border-r">
                                <InputGroupText>EUR</InputGroupText>
                            </InputGroupAddon>
                            <InputGroupInput name="amount" id="amount" type="number" step={100} min={2000} max={10000} value={amount} onChange={event => setAmount(parseInt(event.target.value as string))} placeholder="2000" required />
                        </InputGroup>
                    </div>
                </Field>
                <Field>
                    <div className="flex items-center">
                        <FieldLabel htmlFor="period">Loan period</FieldLabel>
                    </div>
                    <div className="flex items-center gap-5">
                        <div className="w-full pt-7">
                            <Slider min={12} max={60} value={period} onValueChange={value => setPeriod(value as number)} />
                            <div className="flex items-center justify-between gap-2 text-sm text-muted-foreground mt-3.5">
                                <span>12</span>
                                <span>60</span>
                            </div>
                        </div>
                        <InputGroup className="h-16 rounded-[1vw]">
                            <InputGroupAddon className="pr-2 border-r">
                                <InputGroupText>MON</InputGroupText>
                            </InputGroupAddon>
                            <InputGroupInput name="period" id="period" type="number" min={12} max={60} value={period} onChange={event => setPeriod(parseInt(event.target.value as string))} placeholder="12" required />
                        </InputGroup>
                    </div>
                </Field>
                <Field>
                    {
                        loading
                            ? <Button className="h-16" disabled>
                                <Leapfrog size="40" speed="2.5" color="var(--primary-foreground)"/>
                            </Button>
                            : <Button className="h-16 cursor-pointer" type="submit">
                                Decide
                            </Button>
                    }
                </Field>
            </FieldGroup>
        </form>
    );
};