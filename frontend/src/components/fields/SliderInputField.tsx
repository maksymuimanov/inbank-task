import {Field, FieldLabel} from "@/components/ui/field.tsx";
import {Slider} from "@/components/ui/slider.tsx";
import {InputGroup, InputGroupAddon, InputGroupInput, InputGroupText} from "@/components/ui/input-group.tsx";
import {useState} from "react";

export const SliderInputField = (
    {
        name,
        label,
        addon,
        step,
        min,
        max
    }: {
        name: string,
        label: string,
        addon: string,
        step: number,
        min: number,
        max: number
    }) => {
    const [value, setValue] = useState<number>(min);

    return (
        <Field>
            <div className="flex items-center">
                <FieldLabel htmlFor={name}>{label}</FieldLabel>
            </div>
            <div className="flex items-center gap-5">
                <div className="w-full pt-7">
                    <Slider
                        step={step}
                        min={min}
                        max={max}
                        value={value}
                        onValueChange={value => setValue(value as number)}
                    />
                    <div className="flex items-center justify-between gap-2 text-sm text-muted-foreground mt-3.5">
                        <span>{min}</span>
                        <span>{max}</span>
                    </div>
                </div>
                <InputGroup className="flex-1/2 h-16 rounded-[1vw]">
                    <InputGroupAddon className="pr-2 border-r">
                        <InputGroupText>{addon}</InputGroupText>
                    </InputGroupAddon>
                    <InputGroupInput
                        name={name}
                        id={name}
                        type="number"
                        step={step}
                        min={min}
                        max={max}
                        value={value}
                        onChange={event => setValue(parseInt(event.target.value as string))}
                        placeholder={min.toString()}
                        required
                    />
                </InputGroup>
            </div>
        </Field>
    );
};